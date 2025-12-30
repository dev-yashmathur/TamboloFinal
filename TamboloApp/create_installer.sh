#!/bin/bash

# Default Configuration
APP_NAME="Tambolo"
VERSION="${1:-1.0}" # Use first argument as version, default to 1.0
SRC_DIR="src"
BUILD_DIR="build"
INSTALLER_DIR="${BUILD_DIR}/installer"
JAR_FILE="${BUILD_DIR}/libs/tambolo.jar"
MAIN_CLASS="tambolo.UI"

# Ensure we are in the project root
if [ ! -d "$SRC_DIR" ]; then
    echo "Error: 'src' directory not found. Please run this script from the project root."
    exit 1
fi

echo "=== Building ${APP_NAME} version ${VERSION} ==="

# 1. Clean and Compile
echo "[1/5] Cleaning and Compiling..."
rm -rf "${BUILD_DIR}"
mkdir -p "${BUILD_DIR}/classes"
mkdir -p "${BUILD_DIR}/libs"

# Compile module-info and source files
javac -d "${BUILD_DIR}/classes" "${SRC_DIR}/module-info.java" "${SRC_DIR}/tambolo/"*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

# 2. Create JAR
echo "[2/5] Creating JAR file..."
jar --create --file "${JAR_FILE}" --main-class "${MAIN_CLASS}" --module-version "${VERSION}" -C "${BUILD_DIR}/classes" .

# 3. Prepare jpackage inputs
echo "[3/5] preparing DMG resources..."
# We create a temporary link. jpackage uses this to trigger the 'folder view' layout.
# We will remove the resulting folder from the DMG later.
rm -f "${BUILD_DIR}/Applications_Link"
ln -s /Applications "${BUILD_DIR}/Applications_Link"

# 4. Run jpackage
echo "[4/5] Running jpackage (this may take a moment)..."
mkdir -p "${INSTALLER_DIR}"
jpackage --name "${APP_NAME}" \
  --input "${BUILD_DIR}/libs" \
  --main-jar "tambolo.jar" \
  --type dmg \
  --dest "${INSTALLER_DIR}" \
  --mac-dmg-content "${BUILD_DIR}/Applications_Link" \
  --app-version "${VERSION}"

# Define file paths
INITIAL_DMG="${INSTALLER_DIR}/${APP_NAME}-${VERSION}.dmg"
RW_DMG="${INSTALLER_DIR}/${APP_NAME}-RW.dmg"
EDITABLE_DMG="${INSTALLER_DIR}/${APP_NAME}-Editable.dmg"
FINAL_DMG="${INITIAL_DMG}" # We will overwrite the initial one

if [ ! -f "${INITIAL_DMG}" ]; then
    echo "Error: jpackage failed to create ${INITIAL_DMG}"
    exit 1
fi

# 5. Post-process DMG (Remove extra folder)
echo "[5/5] Finalizing DMG layout..."

# Convert to Read/Write
mv "${INITIAL_DMG}" "${RW_DMG}"
hdiutil convert "${RW_DMG}" -format UDRW -o "${EDITABLE_DMG}" -quiet

# Mount the DMG
MOUNT_POINT="${BUILD_DIR}/mnt"
mkdir -p "${MOUNT_POINT}"
hdiutil attach "${EDITABLE_DMG}" -mountpoint "${MOUNT_POINT}" -nobrowse -quiet

# Remove the unwanted "Applications_Link" folder
# (The 'Applications' shortcut is already safe inside the .app bundle or view due to jpackage magic, 
# but specifically the folder copied from the --mac-dmg-content link needs to go)
rm -rf "${MOUNT_POINT}/Applications_Link"

# Unmount
hdiutil detach "${MOUNT_POINT}" -quiet

# Convert back to compressed DMG (Overwrite original)
hdiutil convert "${EDITABLE_DMG}" -format UDZO -o "${FINAL_DMG}" -quiet

# Cleanup intermediate files
rm "${RW_DMG}" "${EDITABLE_DMG}"
rmdir "${MOUNT_POINT}"
rm "${BUILD_DIR}/Applications_Link"

echo "=== Success! ==="
echo "Installer created at: ${FINAL_DMG}"
