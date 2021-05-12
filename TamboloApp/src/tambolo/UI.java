package tambolo;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

public class UI {
//	JFrame welcome_frame;
	Font UIFont = new Font(Font.MONOSPACED, Font.BOLD, 48);
	Font Smaller = new Font(Font.MONOSPACED, Font.ITALIC, 32);
	Font caution = new Font(Font.SANS_SERIF, Font.BOLD, 24);
	Color bg = new Color(196, 250, 248);
	Border Padding = BorderFactory.createEmptyBorder(35, 35, 35, 35);
	
	void welcome_frame_init() {
		JFrame welcome_frame = new JFrame();
		JPanel Welcome_Panel = new JPanel();
		Welcome_Panel.setLayout(new GridLayout(4, 1, 20, 10));
		Welcome_Panel.setBorder(Padding);
		Welcome_Panel.setBackground(bg);
		JLabel welcome_text_1 = new JLabel("Welcome\n");
		JLabel welcome_text_2 = new JLabel("To");
		JLabel welcome_text_3 = new JLabel("Automated Tambolo");
		JLabel welcome_text_4 = new JLabel("Press any key to continue...");
		
		welcome_text_1.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_2.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_3.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_4.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_1.setFont(UIFont);
		welcome_text_2.setFont(UIFont);
		welcome_text_3.setFont(UIFont);
		welcome_text_4.setFont(UIFont);
		
		Welcome_Panel.add(welcome_text_1);
		Welcome_Panel.add(welcome_text_2);
		Welcome_Panel.add(welcome_text_3);
		Welcome_Panel.add(welcome_text_4);
		
		welcome_frame.add(Welcome_Panel);
		welcome_frame.pack();
		welcome_frame.setVisible(true);
		welcome_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		welcome_frame.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				welcome_frame.dispose();
				first_frame_init();
			}
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }
			
		});
	}
	
	void first_frame_init() {
		JFrame first_frame = new JFrame();
		JPanel input_panel = new JPanel();
		input_panel.setBackground(bg);
		input_panel.setLayout(new GridBagLayout());
		input_panel.setBorder(Padding);
		JLabel input_label = new JLabel("Enter num of tickets: ");
		input_label.setFont(Smaller);
		JLabel caution_label = new JLabel("Please enter a number");
		caution_label.setFont(caution);
		caution_label.setForeground(Color.red);
		
		JTextField input = new JTextField(10);
		input.setFont(UIFont);
		input.setBackground(Color.white);
		input.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		//for input_label
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		input_panel.add(input_label, gbc);
		//for Text Field
		gbc.gridx = 1;
		gbc.gridy = 0;
		input_panel.add(input, gbc);
		
		//gridy = 1 is used for caution
		input.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }	
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				char current_letter = e.getKeyChar();
				if(!Character.isDigit(current_letter) && current_letter != 65535 && current_letter >10 ) {
					try {
					String current = input.getText();
					current = current.substring(0, current.length()-1);
					input.setText(current);
					
					gbc.gridx = 1;
					gbc.gridy = 1;
					input_panel.add(caution_label, gbc);
					first_frame.revalidate();
					first_frame.pack();
					} catch(Exception excep) {}
				}
			}
			
		});
		
		
		first_frame.add(input_panel);
		first_frame.pack();
		first_frame.setVisible(true);
		first_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		UI ob = new UI();
		ob.welcome_frame_init();
		
	}
}
