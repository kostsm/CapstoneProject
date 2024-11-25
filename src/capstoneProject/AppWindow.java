package capstoneProject;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.apple.eawt.Application;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;



import layout.SpringUtilities;


public class AppWindow extends JFrame{
	static Battery mainBattery = new Battery("mainBattery", 1000);
	static EnergySystem engSys;
	
	
	List<ChargingStation> chargingStations;
    List<EnergySource> energySources;
    static ExecutorService executorService = Executors.newCachedThreadPool();
	
	JLabel lbl_numSrc;
	JLabel lbl_numCons;
	JLabel lbl_numBat;
	JLabel lbl_pwrProd;
	JLabel lbl_pwrCons;
	JLabel lbl_battCap;

	JButton	btn_addSrc;
	JButton	btn_addCons;
	JButton	btn_addBatt;
	JButton	btn_adjSrc;
	JButton	btn_adjCons;
	JButton	btn_adjBatt;
	JButton	btn_saveLog;
	JButton	btn_loadConf;
	


	public AppWindow() throws IOException {
		engSys = new EnergySystem();
		
		
		
		
//		GridLayout mainLayout = new GridLayout(0,3);
		
		this.getContentPane().setLayout(new GridBagLayout());

		this.initWindow();
		
		this.addWindowListener(new WindowListener() {

			public void windowClosed(WindowEvent arg0) {


			}

			public void windowActivated(WindowEvent e) {


			}

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowDeactivated(WindowEvent e) {


			}

			public void windowDeiconified(WindowEvent e) {


			}

			public void windowIconified(WindowEvent e) {


			}

			public void windowOpened(WindowEvent e) {


			}



		});

	}

	protected void initWindow() 
	{
		this.setLocationRelativeTo(null);
		// Instanciate:		
		GridBagConstraints gbc = new GridBagConstraints();
		btn_addSrc = new JButton ("Add source");
		btn_addCons = new JButton ("Add consumer");
		btn_adjSrc = new JButton ("Adjust source");
		btn_adjCons = new JButton ("Adjust consumer");
		btn_adjBatt = new JButton ("Adjust battery");
		btn_saveLog = new JButton ("Save logfile");
		btn_loadConf = new JButton ("Load config");

		lbl_numSrc = new JLabel("# sources: 0");
		lbl_numCons = new JLabel("# consumers: 0");
		lbl_pwrProd = new JLabel("total power production: 0kWh");
		lbl_pwrCons = new JLabel("total power consumption: 0kWh");
		lbl_battCap = new JLabel("total battery capacity: 0kWh");

		btn_addSrc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					addEnergySource();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		btn_addCons.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					addConsumer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btn_adjSrc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				adjSrc();
			}
		});
		
		btn_adjCons.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				adjCons();
			}
		});
		
		btn_saveLog.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				LogFileManager logFileManager = new LogFileManager();
				try {
					for (ChargingStation chrg : chargingStations) {
						logFileManager.createLog(chrg.getName(), LocalDate.now()) ;
					}
					for (EnergySource src : energySources) {
						logFileManager.createLog(src.getName(), LocalDate.now()) ;
					}
				}catch (IOException e) {
					e.printStackTrace();
				} catch (ChainException e) {
					e.printStackTrace();
				}
			}
		});
		
		btn_loadConf.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				readConf();
				updateWindow();
			}
		});
		
		// Add Elements to Window:
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(lbl_numSrc, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
//        gbc.gridwidth = 1;
        this.getContentPane().add(lbl_numCons, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.getContentPane().add(lbl_pwrProd, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.getContentPane().add(lbl_pwrCons, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        this.getContentPane().add(btn_addSrc, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.getContentPane().add(btn_addCons, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.getContentPane().add(btn_adjSrc, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.getContentPane().add(btn_adjCons, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.getContentPane().add(btn_saveLog, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        this.getContentPane().add(btn_loadConf, gbc);
        
		this.pack();
	}
	
	public void readConf() {
		JFileChooser j = new JFileChooser();
		String filePath = null;
		 
        // invoke the showsOpenDialog function to show the save dialog
        int r = j.showOpenDialog(null);

        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION)

        {
            // set the label to the path of the selected file
            filePath = j.getSelectedFile().getAbsolutePath();
            System.out.println(filePath);
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    processLine(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
        	System.out.println("user aborted file selection");
        }
        
        
	}
	
	public static void processLine(String line) throws IOException {
        String[] attributes = line.split(";");
        String objectType = null;
        String name = null, location = null, typeOfSource = null, battery_name = null;
        float maxPowerConsumption = 0, maxPowerProduction = 0;
        int capacity = 0;

        for (String attribute : attributes) {
            String[] keyValue = attribute.split("=");
            String key = keyValue[0].replaceAll("\"", "").trim();
            String value = keyValue[1].replaceAll("\"", "").trim();
            

            switch (key) {
                case "object_type":
                    objectType = value;
                    break;
                case "name":
                    name = value;
                    break;
                case "location":
                    location = value;
                    break;
                case "maxPowerConsumption":
                    maxPowerConsumption = Float.parseFloat(value);
                    break;
                case "typeOfSource":
                    typeOfSource = value;
                    break;
                case "maxPowerProduction":
                    maxPowerProduction = Float.parseFloat(value);
                    break;
                case "capacity":
                    capacity = Integer.parseInt(value);
                    break;
                case "battery_name":
                	battery_name = value;
            }
        }

        switch (objectType) {
            case "chargingStation":
            	ChargingStation chargingStation;
				chargingStation = new ChargingStation(name, location, maxPowerConsumption, mainBattery);
				engSys.addChargingStation(chargingStation);
				executorService.submit(chargingStation);
				break;
            case "powerSource":
                EnergySource powerSource = new EnergySource(name, typeOfSource, maxPowerProduction, mainBattery);
                engSys.addEnergySource(powerSource);
                executorService.submit(powerSource);
                break;
            case "battery":
                Battery mainBattery = new Battery(name, capacity);
                break;
        }
	}
	
	public void updateWindow() {
    	chargingStations = engSys.getChargingStations();
    	energySources = engSys.getEnergySources();
    	
    	int numSrc = 0;
    	int numCons = 0;
    	float totalPowerConsumption = 0;
    	float totalPowerProduction = 0;
    	// check total power consumption
    	for (ChargingStation chrg : chargingStations) {
    		totalPowerConsumption +=chrg.getCurrentPower();
    		numCons +=1;
        }
    	
    	// check total power production
    	for (EnergySource src : energySources) {
    		totalPowerProduction +=src.getCurrentPower(); 
    		numSrc +=1;
    	}
		lbl_numSrc.setText("# sources: " + numSrc);
		lbl_numCons.setText("# consumers: " + numCons);
		lbl_pwrProd.setText("total power production: " + totalPowerProduction + "kWh");
		lbl_pwrCons.setText("total power consumption: " + totalPowerConsumption + "kWh");
	}

	public void addEnergySource() throws IOException {
		JFrame frame = new JFrame("Add src");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           e.printStackTrace();
        }
		  
        // set panel 
        JPanel p = new JPanel(new SpringLayout()); 
//        p.setLayout();
		
        JTextField txtfield_name = new JTextField();
        JTextField 	txtfield_type = new JTextField();
        JTextField 	txtfield_maxPower = new JTextField();
        JLabel lbl_name = new JLabel("Name: ");
        JLabel lbl_type = new JLabel("Type: ");
        JLabel lbl_maxPower = new JLabel("max. Power: ");
        JButton btn_saveSrc = new JButton ("Save source");
        JButton btn_cancel = new JButton ("Cancel");
        lbl_name.setLabelFor(txtfield_name);
        lbl_type.setLabelFor(txtfield_type);
        lbl_maxPower.setLabelFor(txtfield_maxPower);
        p.add(lbl_name);
        p.add(txtfield_name);
        p.add(lbl_type);
        p.add(txtfield_type);
        p.add(lbl_maxPower);
        p.add(txtfield_maxPower);
        p.add(btn_cancel);
        p.add(btn_saveSrc);
        
        p.setOpaque(true); 
        
        SpringUtilities.makeCompactGrid(p,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        
        frame.setContentPane(p);;
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        
        btn_saveSrc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					String sourceName = txtfield_name.getText();
			        String sourceType = txtfield_type.getText();
			        int sourceMaxPower = Integer.parseInt(txtfield_maxPower.getText());
			        
			        EnergySource engSource = new EnergySource(sourceName, sourceType, sourceMaxPower, mainBattery);
			        engSys.addEnergySource(engSource);
			        executorService.submit(engSource);
			        updateWindow();
			        frame.dispose();
			        
			        
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
        
        btn_cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				updateWindow();
				frame.dispose();
			}
		}); 
	}
	
	public void addConsumer() throws IOException {
		JFrame frame = new JFrame("Add cons");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           e.printStackTrace();
        }
		  
        // set panel 
        JPanel p = new JPanel(new SpringLayout()); 
//        p.setLayout();
		
        JTextField txtfield_name = new JTextField();
        JTextField 	txtfield_location = new JTextField();
        JTextField 	txtfield_maxPower = new JTextField();
        JLabel lbl_name = new JLabel("Name: ");
        JLabel lbl_location = new JLabel("Location: ");
        JLabel lbl_maxPower = new JLabel("max. Power: ");
        JButton btn_saveSrc = new JButton ("Save consumer");
        JButton btn_cancel = new JButton ("Cancel");
        lbl_name.setLabelFor(txtfield_name);
        lbl_location.setLabelFor(txtfield_location);
        lbl_maxPower.setLabelFor(txtfield_maxPower);
        p.add(lbl_name);
        p.add(txtfield_name);
        p.add(lbl_location);
        p.add(txtfield_location);
        p.add(lbl_maxPower);
        p.add(txtfield_maxPower);
        p.add(btn_cancel);
        p.add(btn_saveSrc);
        
        p.setOpaque(true); 
        
        SpringUtilities.makeCompactGrid(p,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        
        frame.setContentPane(p);;
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        
        btn_saveSrc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					String consName = txtfield_name.getText();
			        String consLocation = txtfield_location.getText();
			        int consMaxPower = Integer.parseInt(txtfield_maxPower.getText());
			        
			        ChargingStation consumer = new ChargingStation(consName, consLocation, consMaxPower, mainBattery);
			        engSys.addChargingStation(consumer);
			        executorService.submit(consumer);
			        updateWindow();
			        frame.dispose();
			           
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
        
        btn_cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				updateWindow();
				frame.dispose();
			}
		});
	}
	
	public void adjSrc() {
		JFrame frame = new JFrame("Adj src");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 200);
        JButton btn_save = new JButton ("Save");
        JButton btn_cancel = new JButton ("Cancel");
		try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           e.printStackTrace();
        }
		  
		frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel p = new JPanel();
        JLabel lblNewPower = new JLabel("Power Percentage: ");
        JTextField txtNewPower = new JTextField();
        lblNewPower.setLabelFor(txtNewPower);
        
		List<String> s1 = new ArrayList<String>();
		energySources = engSys.getEnergySources();
		for (EnergySource src : energySources) {
    		s1.add(src.getName());
    	}
		String[] simpleArray = new String[s1.size()];
		s1.toArray(simpleArray);
		final JComboBox<String> cb = new JComboBox<String>(simpleArray);

	    cb.setVisible(true);
	    
	    gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(cb, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(lblNewPower, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(txtNewPower, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(btn_cancel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(btn_save, gbc);
	    
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String srcName = cb.getSelectedItem().toString();
				float newPower = Float.parseFloat(txtNewPower.getText());
				
				for (EnergySource src : energySources) {
					if (srcName.equals(src.getName())) {
						src.setPower(newPower);
					}
				}
				updateWindow();
				frame.dispose();
			}
		});
        
        btn_cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				updateWindow();
				frame.dispose();
			}
		});
        

		
	}
	
	public void adjCons() {
		JFrame frame = new JFrame("Adj cons");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 200);
        JButton btn_save = new JButton ("Save");
        JButton btn_cancel = new JButton ("Cancel");
		try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           e.printStackTrace();
        }
		  
		frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel lblNewPower = new JLabel("Power Percentage: ");
        JTextField txtNewPower = new JTextField();
        lblNewPower.setLabelFor(txtNewPower);
        
		List<String> s1 = new ArrayList<String>();
		chargingStations = engSys.getChargingStations();
		for (ChargingStation cons : chargingStations) {
    		s1.add(cons.getName());
    	}
		String[] simpleArray = new String[s1.size()];
		s1.toArray(simpleArray);
		final JComboBox<String> cb = new JComboBox<String>(simpleArray);

	    cb.setVisible(true);
	    
	    gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(cb, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(lblNewPower, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(txtNewPower, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(btn_cancel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(btn_save, gbc);
	    
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chrgName = cb.getSelectedItem().toString();
				float newPower = Float.parseFloat(txtNewPower.getText());
				
				for (ChargingStation chrg : chargingStations) {
					if (chrgName.equals(chrg.getName())) {
						chrg.setPower(newPower);
					}
				}
				updateWindow();
				frame.dispose();
			}

		});
        
        btn_cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				updateWindow();
				frame.dispose();
			}

		});
        

		
	}

	}
