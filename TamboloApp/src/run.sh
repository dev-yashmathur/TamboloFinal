#From inside src folder

#!/bin/bash
mkdir -p out && javac -d out tambolo/*.java
java -cp out tambolo.UI