package tambolo;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class UI {
	Font UIFont = new Font(Font.MONOSPACED, Font.BOLD, 48);
	Font Smaller = new Font(Font.MONOSPACED, Font.ITALIC, 32);
	Font caution = new Font(Font.SANS_SERIF, Font.BOLD, 24);
	Color bg = new Color(110, 202, 255);
	Border Padding = BorderFactory.createEmptyBorder(35, 35, 35, 35);
	
	int Tickets_num;
	JFrame ticket_frame;
	ticketRender[] ticket_list; 
	JPanel ticket_panel;
	
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
				player_details_frame_init();
			}
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }
		});
		
		welcome_frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) { }
			@Override
			public void mousePressed(MouseEvent e) { }
			@Override
			public void mouseClicked(MouseEvent e) {
				welcome_frame.dispose();
				player_details_frame_init();			
			}
			@Override
			public void mouseExited(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }	
		});
	}
	
	void player_details_frame_init() {
		
		JFrame player_details_frame = new JFrame();
		JPanel input_panel = new JPanel();
		input_panel.setBackground(bg);
		input_panel.setLayout(new GridBagLayout());
		input_panel.setBorder(Padding);
		JLabel input_label = new JLabel("Enter num of tickets: ");
		input_label.setFont(Smaller);
		JLabel caution_label = new JLabel("Please enter a number");
		caution_label.setFont(caution);
		caution_label.setForeground(Color.red);
		
		JTextField inputNoOfTickets = new JTextField(10);
		inputNoOfTickets.setFont(UIFont);
		inputNoOfTickets.setBackground(Color.white);
		inputNoOfTickets.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
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
		input_panel.add(inputNoOfTickets, gbc);
		
		JButton enterNames = new JButton("Enter Names");
		JButton goToPrizes = new JButton("Select Prizes");
		
		//gridy = 1 is used for caution
		inputNoOfTickets.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }	
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				char current_letter = e.getKeyChar();
				if(current_letter == 10) {
					enterNames.doClick();
					return;
				}
				
				if(!Character.isDigit(current_letter) && current_letter != 65535 && current_letter >10 ) {
					try {
					String current = inputNoOfTickets.getText();
					current = current.substring(0, current.length()-1);
					inputNoOfTickets.setText(current);
					
					gbc.gridx = 1;
					gbc.gridy = 1;
					input_panel.add(caution_label, gbc);
					player_details_frame.revalidate();
					player_details_frame.pack();
					} catch(Exception excep) {}
				}
			}
			
		});
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		
		input_panel.add(enterNames, gbc);
		ArrayList<JPanel> name_panel = new ArrayList<>();
		ArrayList<JLabel> name_label = new ArrayList<>();
		ArrayList<JTextField> name_field = new ArrayList<>();
		
		enterNames.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Tickets_num = Integer.parseInt(inputNoOfTickets.getText());
				} catch(Exception excep) {
					gbc.gridx = 1;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					input_panel.add(caution_label, gbc);
					player_details_frame.revalidate();
					player_details_frame.pack();
					return;
				}
				for(int j = name_panel.size()-1; j>=0; j--) {
					input_panel.remove(name_panel.get(j));
					name_panel.remove(j);
					name_label.remove(j);
					name_field.remove(j);
					player_details_frame.revalidate();
				}
				for(int i = 0; i<Tickets_num; i++) {
					name_panel.add(new JPanel());
					name_panel.get(i).setBackground(bg);
					name_label.add(new JLabel("Enter Name " + Integer.toString(i+1) + ": "));
					name_field.add(new JTextField(50));
					name_label.get(i).setFont(Smaller);
					name_field.get(i).setFont(Smaller);
					name_panel.get(i).add(name_label.get(i));
					name_panel.get(i).add(name_field.get(i));
					gbc.gridy = 2+1+i;//2 for previous, 1 cause i starts from 0
					gbc.gridx = 0;
					gbc.gridwidth =2;
					input_panel.add(name_panel.get(i), gbc);
					player_details_frame.pack();
					player_details_frame.revalidate();	
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 10000; //ensuring always last
		gbc.gridwidth = 2;
		input_panel.add(goToPrizes, gbc);		
		goToPrizes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Tickets_num != Integer.parseInt(inputNoOfTickets.getText())) {
						enterNames.doClick();
						return;
					} 
				}catch(Exception excep) {
					enterNames.doClick();
					return;
				}
				StaticItems.player_names = new String[Tickets_num];
				for(int i = 0; i<Tickets_num; i++) {
					try {
						StaticItems.player_names[i] = name_field.get(i).getText() + " :: " + Integer.toString(i+1);
					}catch(NullPointerException excep) {
						StaticItems.player_names[i] = Integer.toString(i+1);
					}
				}
				
				player_details_frame.dispose();
				prize_frame_init();
			}
		});
		
		goToPrizes.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10)
					goToPrizes.doClick();
			}
		});
		enterNames.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10)
					enterNames.doClick();
			}
		});
		
		JScrollPane jsp = new JScrollPane(input_panel);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		player_details_frame.add(jsp);
		player_details_frame.pack();
		player_details_frame.setVisible(true);
		player_details_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	void prize_frame_init() {
		//INITIALIZING VARIABLES USED THROUGHOUT THE FUNCTION
		JFrame prize_frame = new JFrame();
		JPanel prize_panel = new JPanel();
		JLabel caution_label = new JLabel("Please enter a number");
		JLabel introToPrizes = new JLabel("<html><u>Select The Prizes To Play With</u></html>");
		prize_panel.setLayout(new GridBagLayout());
		prize_panel.setBorder(Padding);
		prize_panel.setBackground(bg);
		caution_label.setFont(caution);
		caution_label.setForeground(Color.red);	
		introToPrizes.setFont(UIFont);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		prize_panel.add(introToPrizes, gbc);
		
		
		JCheckBox FIRST_N = new JCheckBox("First to N"); //gridy = 1
		JPanel FIRST_N_Panel = new JPanel();
		JLabel FIRST_N_Label = new JLabel("Enter First To: ");
		JTextField FIRST_N_FIELD = new JTextField(20);
		FIRST_N_Panel.setLayout(new FlowLayout());
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		prize_panel.add(FIRST_N, gbc);
		FIRST_N.setFont(Smaller);
		FIRST_N_Label.setFont(Smaller);	
		FIRST_N_FIELD.setFont(Smaller);	
		FIRST_N.setBackground(bg);
		FIRST_N_Panel.setBackground(bg);
		FIRST_N_Panel.add(FIRST_N_Label);
		FIRST_N_Panel.add(FIRST_N_FIELD);
		FIRST_N_Panel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		
		
		JCheckBox LINES = new JCheckBox("Lines"); //gridy = 2
		JPanel numOfLines_panel = new JPanel();
		JLabel numOfLines_Label = new JLabel("Enter No. of Lines:");
		JTextField numOfLines_field = new JTextField(10); //gridy = 2		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		prize_panel.add(LINES, gbc);
		LINES.setFont(Smaller);
		numOfLines_Label.setFont(Smaller);
		numOfLines_field.setFont(Smaller);
		LINES.setBackground(bg);
		numOfLines_panel.setBackground(bg);
		numOfLines_panel.add(numOfLines_Label);
		numOfLines_panel.add(numOfLines_field);
		numOfLines_panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		
		JCheckBox HOUSES = new JCheckBox("Houses"); //gridy = 2
		JPanel numOfHouses_panel = new JPanel();
		JLabel numOfHouses_Label = new JLabel("Enter No. of Houses:");
		JTextField numOfHouses_field = new JTextField(10); //gridy = 2		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		prize_panel.add(HOUSES, gbc);
		HOUSES.setFont(Smaller);
		numOfHouses_Label.setFont(Smaller);
		numOfHouses_field.setFont(Smaller);
		HOUSES.setBackground(bg);
		numOfHouses_panel.setBackground(bg);
		numOfHouses_panel.add(numOfHouses_Label);
		numOfHouses_panel.add(numOfHouses_field);
		numOfHouses_panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		
		JButton goToPlay = new JButton("PLAY!");		
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 5; //Caution Label is in 4
		goToPlay.setFont(Smaller);
		prize_panel.add(goToPlay, gbc);
		
		
		FIRST_N.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10) {
					FIRST_N.doClick();
				}
			}
		});
		FIRST_N.addActionListener(new ActionListener() { //gridy = 1;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(FIRST_N.isSelected()) {
					gbc.gridx = 1;
					gbc.gridwidth = 1;
					gbc.gridy = 1;
					prize_panel.add(FIRST_N_Panel,gbc);
					prize_panel.remove(FIRST_N);
					gbc.gridx = 0;
					gbc.anchor = GridBagConstraints.EAST;
					prize_panel.add(FIRST_N, gbc);
					gbc.anchor = GridBagConstraints.CENTER;
					prize_frame.revalidate();
					prize_frame.pack();
				}
				else {
					prize_panel.remove(FIRST_N_Panel);
					prize_panel.remove(FIRST_N);
					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 2;
					prize_panel.add(FIRST_N, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
				}
			}
		});
		FIRST_N_FIELD.addKeyListener(new KeyListener() { //gridy = 3 = caution
			@Override
			public void keyTyped(KeyEvent e) { }	
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				char current_letter = e.getKeyChar();
				if(!Character.isDigit(current_letter) && current_letter != 65535 && current_letter >10 ) {
					try {
					String current = FIRST_N_FIELD.getText();
					current = current.substring(0, current.length()-1);
					FIRST_N_FIELD.setText(current);
					
					gbc.gridx = 1;
					gbc.gridy = 4;
					prize_panel.add(caution_label, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
					} catch(Exception excep) {} //out of bounds
				}
			}
		});
		
		LINES.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }	
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10) {
					LINES.doClick();
				}
			}
		});
		LINES.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LINES.isSelected()) {
					gbc.gridx = 1;
					gbc.gridy = 2;
					gbc.weightx = 0.3;
					gbc.gridwidth = 1;
					prize_panel.add(numOfLines_panel, gbc);
					prize_panel.remove(LINES);
					gbc.gridx = 0;
					gbc.gridy = 2;
					gbc.gridwidth = 1;
					gbc.anchor = GridBagConstraints.EAST;
					prize_panel.add(LINES, gbc);
					gbc.anchor = GridBagConstraints.CENTER;
					prize_frame.revalidate();
					prize_frame.pack();
				}
				else {
					prize_panel.remove(numOfLines_panel);
					prize_panel.remove(LINES);
					gbc.gridx = 0;
					gbc.gridy = 2;
					gbc.gridwidth = 2;
					prize_panel.add(LINES, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
				}
			}
		});
		numOfLines_field.addKeyListener(new KeyListener() { //gridy = 3 = caution
			@Override
			public void keyTyped(KeyEvent e) { }	
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				char current_letter = e.getKeyChar();
//				if(current_letter == 10) {
//					goToPlay.doClick();
//				} 
//				Commenting since multiple prizes hence not appropriate
				if(!Character.isDigit(current_letter) && current_letter != 65535 && current_letter >10 ) {
					try {
					String current = numOfLines_field.getText();
					current = current.substring(0, current.length()-1);
					numOfLines_field.setText(current);
					
					gbc.gridx = 1;
					gbc.gridy = 4;
					prize_panel.add(caution_label, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
					} catch(Exception excep) {} //out of bounds
				}
			}
			
		});
		//houses below
		HOUSES.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }	
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10) {
					HOUSES.doClick();
				}
			}
		});
		HOUSES.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(HOUSES.isSelected()) {
					gbc.gridx = 1;
					gbc.gridy = 3;
					gbc.weightx = 0.3;
					gbc.gridwidth = 1;
					prize_panel.add(numOfHouses_panel, gbc);
					prize_panel.remove(HOUSES);
					gbc.gridx = 0;
					gbc.gridy = 3;
					gbc.gridwidth = 1;
					gbc.anchor = GridBagConstraints.EAST;
					prize_panel.add(HOUSES, gbc);
					gbc.anchor = GridBagConstraints.CENTER;
					prize_frame.revalidate();
					prize_frame.pack();
				}
				else {
					prize_panel.remove(numOfHouses_panel);
					prize_panel.remove(HOUSES);
					gbc.gridx = 0;
					gbc.gridy = 3;
					gbc.gridwidth = 2;
					prize_panel.add(HOUSES, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
				}
			}
		});
		numOfHouses_field.addKeyListener(new KeyListener() { //gridy = 3 = caution
			@Override
			public void keyTyped(KeyEvent e) { }	
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				char current_letter = e.getKeyChar();
//				if(current_letter == 10) {
//					goToPlay.doClick();
//				} 
//				Commenting since multiple prizes hence not appropriate
				if(!Character.isDigit(current_letter) && current_letter != 65535 && current_letter >10 ) {
					try {
					String current = numOfHouses_field.getText();
					current = current.substring(0, current.length()-1);
					numOfHouses_field.setText(current);
					
					gbc.gridx = 1;
					gbc.gridy = 4;
					prize_panel.add(caution_label, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
					} catch(Exception excep) {} //out of bounds
				}
			}
			
		});

		
		
		
		goToPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				if(LINES.isSelected()) {
					try {
						n = Integer.parseInt(numOfLines_field.getText());
					}catch(Exception excep) {
						gbc.gridx = 1;
						gbc.gridy = 4;
						gbc.gridwidth = 1;
						prize_panel.add(caution_label, gbc);
						prize_frame.revalidate();
						prize_frame.pack();
						return;
					}
					Prizes.LINE_PRIZES = new Boolean[n];
					for(int i = 0; i<n; i++) {
						Prizes.LINE_PRIZES[i] = true;
					}
				}//do above if LINES is selected
				
				int n2 = -1;
				if(FIRST_N.isSelected()) {try {
					n2 = Integer.parseInt(FIRST_N_FIELD.getText());
				}catch(Exception excep) {
					gbc.gridx = 1;
					gbc.gridy = 4;
					gbc.gridwidth = 1;
					prize_panel.add(caution_label, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
					return;
				}
				Prizes.FIRST_N = true;
				Prizes.First_To = n2;
				}
				
				
				if(HOUSES.isSelected()) {
					int n3 = -1;
					try {
						n3 = Integer.parseInt(numOfHouses_field.getText());
					}catch(Exception excep) {
						gbc.gridx = 1;
						gbc.gridy = 4;
						gbc.gridwidth = 1;
						prize_panel.add(caution_label, gbc);
						prize_frame.revalidate();
						prize_frame.pack();
						return;
					}
					Prizes.FULL_HOUSE = true;
					Prizes.FULL_HOUSE_NO = n3;
				}//do above if HOUSES is selected
				prize_frame.dispose();
				play_frame_init();
			}
		});
		goToPlay.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10) {
					goToPlay.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) { }
		});
		
		
		prize_frame.add(prize_panel);
		prize_frame.pack();
		prize_frame.setVisible(true);
		prize_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	void ticket_frame_init() {
		ticket_frame = new JFrame();
		ticket_panel = new JPanel();
		ticket_panel.setLayout(new GridBagLayout());
		ticket_panel.setBackground(bg);
		ticket_panel.setBorder(Padding);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		
		ticket_list = new ticketRender[Tickets_num];
		for(int i = 0; i<Tickets_num; i++) {
			gbc.gridy++;
			ticket_list[i] = new ticketRender(StaticItems.player_names[i]);
			ticket_panel.add(ticket_list[i], gbc);
		}
		JScrollPane jsp = new JScrollPane(ticket_panel);
		ticket_frame.add(jsp);
		ticket_frame.pack();
		ticket_frame.setVisible(true);
		ticket_frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}
	
	void play_frame_init() {
		ticket_frame_init();
		
		String[] prizeColNames = {"Name", "Prize", "Claimed"};
		@SuppressWarnings("serial")
		DefaultTableModel tabmod = new DefaultTableModel(prizeColNames, 0) { 
			@Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 2)
                	return Boolean.class;
                else
                	return String.class;
            }
		};
		JTable prize_winner_listing = new JTable(tabmod);
		JScrollPane jsp = new JScrollPane(prize_winner_listing);
		JFrame play_frame = new JFrame();	
		JPanel play_panel = new JPanel();
		JButton next_num = new JButton("Next number");
		JLabel num_display = new JLabel("");
		prize_winner_listing.setBackground(Color.WHITE);
		prize_winner_listing.setFillsViewportHeight(true);
		jsp.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		play_panel.setLayout(new GridBagLayout());
		play_panel.setBackground(bg);
		next_num.setFont(Smaller);
		num_display.setFont(Smaller);
		prize_winner_listing.setRowHeight(40);
		
		for(int i = StaticItems.lowerLimit; i<=StaticItems.upperLimit; i++)
			StaticItems.left.add(i);
		Collections.shuffle(StaticItems.left);
	
		//NECT NUM BUTTON CLICK
		next_num.addActionListener(new ActionListener() {		
			int houses_done = 0;	
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean houseThisTurn = false;
				//removing unnecessary rows and also checking for claimed prizes
				while(tabmod.getRowCount()>0) {
					if(prize_winner_listing.getValueAt(0, 2) != null && (boolean)prize_winner_listing.getValueAt(0, 2) == true) {
						String s = (String) prize_winner_listing.getValueAt(0, 1);
						s = s.toUpperCase();
						if(s.indexOf("LINE") != -1) {
							int line = Integer.parseInt(s.replaceAll("[^0-9]", ""));
							Prizes.LINE_PRIZES[line-1] = false;
						}
						else if(s.indexOf("HOUSE") != -1) {		
							if(!houseThisTurn) {
								houses_done++;
								houseThisTurn = true;
							}
							System.out.println("HOUSE DONE" + houses_done);
							if(houses_done >= Prizes.FULL_HOUSE_NO) {
								Prizes.FULL_HOUSE = false;
							}
						}
						else if(s.indexOf("FIRST") != -1) {
							Prizes.FIRST_N = false;
						}
						else if(s.indexOf("CORNERS") != -1) {
							Prizes.RAILWAYS = false;
						}
					}
					tabmod.removeRow(0);
				} //completed checking prize claims
				
				
				int current_num = -1; //taking new number 
				try { //TO check for when the final number is out
					current_num = StaticItems.left.get(0); 
					StaticItems.left.remove(0);
				} catch(IndexOutOfBoundsException IOB) {
					num_display.setText("The End");
					return;
				}
				if(current_num != -1) {
					StaticItems.done.add(0, current_num); //adding it to completed list once done.
				}
				num_display.setText(Integer.toString(current_num));
				for(int ticket_count = 0; ticket_count <Tickets_num; ticket_count++) {
					rc_container ob = new rc_container();
					ob = ticket_list[ticket_count].checkForNum(current_num);
					if(ob.present) {
						ticket_list[ticket_count].repaint();
						try {
							if(Prizes.LINE_PRIZES[ob.row] == true) {
								if(Prize_Check.LINES(ticket_list[ticket_count], ob.row)) {
									tabmod.addRow(new Object[] {ticket_list[ticket_count].nameOfHolder, "LINE: " + (ob.row +1)});
									//System.out.println("LINE " + (ob.row+1) + " DONE");
								}
							}
						}catch (Exception excep) {
							
						}
						if(Prizes.RAILWAYS != null && Prizes.RAILWAYS == true) {
							if(Prize_Check.CORNERS(ticket_list[ticket_count])) {
								//System.out.println("CORNERS DONE");
								tabmod.addRow(new Object[] {ticket_list[ticket_count].nameOfHolder, "Corners"});
							}
						}
						if(Prizes.FULL_HOUSE != null && Prizes.FULL_HOUSE == true) {
							if(Prize_Check.HOUSIE(ticket_list[ticket_count])) {
								//System.out.println("HOUSE DONE");
								tabmod.addRow(new Object[] {ticket_list[ticket_count].nameOfHolder, "FULL HOUSE"});
							}
						}
						if(Prizes.FIRST_N != null && Prizes.FIRST_N == true) {
							//System.out.println("checking first n");
							if(Prize_Check.FIRST_N(ticket_list[ticket_count])) {
								//System.out.println("HOUSE DONE");
								tabmod.addRow(new Object[] {ticket_list[ticket_count].nameOfHolder, "FIRST N"});
							}
						}//ending if checking if prize exists.
					} //ending the if condition corresponding to the number being present in the ticket
				}//Ending the for loop which goes though all the tickets
			}//Ending ActionListener Function
		}); //ending actionListener CLASS
		
		
		JButton ticket_display = new JButton("Show Tickets");
		ticket_display.setFont(Smaller);
		ticket_display.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ticket_frame.setVisible(true);
			}
		});
		
		JButton completedNums = new JButton("Previous Numbers");
		JLabel completedNums_Label = new JLabel("");
		completedNums.setFont(Smaller);
		completedNums_Label.setFont(Smaller);
		completedNums.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prevNosString = "";
				for(int ind = 1; ind <StaticItems.done.size() && ind <=5; ind++) {
					prevNosString = prevNosString.concat(Integer.toString(StaticItems.done.get(ind)) + " ");
				}
				//System.out.println("STRING: " + StaticItems.done.size());
				completedNums_Label.setText(prevNosString);
				play_frame.pack();
				play_frame.revalidate();
			}
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		play_panel.add(next_num, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		play_panel.add(num_display, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;
		play_panel.add(ticket_display, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		play_panel.add(completedNums, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		play_panel.add(completedNums_Label, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		play_panel.add(jsp, gbc);
		
		play_frame.add(play_panel);
		play_frame.pack();
		play_frame.setVisible(true);
		play_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
		UI ob = new UI();
		ob.welcome_frame_init();
		
	}
}
	
