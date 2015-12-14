/*
 * authors: Dominik Juraszek, Konrad Chmiel, Sebastian Brandys
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import Controller.Controller;
import Controller.GraphVisualizationAndButtonsStateActualizer;


public class SimulationGUI extends JFrame {

    private final Controller controller; // controller
    private final GraphVisualizationAndButtonsStateActualizer graphActualizer; // thread that actualizes graph view

    private final ResultsViewPanel resultsPanel;

    /**
     * Creates new form maxklika.
     */
    public SimulationGUI() {
    	
        this.controller = new Controller();
        initComponents();
        
        this.resultsPanel = new ResultsViewPanel(graphPanel, controller);
   generationPanel =new GraphGenerationPanel(graphPanel, controller, tabChoosePanel);
  
        tabChoosePanel.addTab("Symulacja", simulationPanel);
        tabChoosePanel.addTab("Generacja grafu", generationPanel);
        tabChoosePanel.addTab("Symulacja iteracji", resultsPanel);
        graphActualizer = new GraphVisualizationAndButtonsStateActualizer(controller, graphPanel, stopButton, startButton, tabChoosePanel,((GraphGenerationPanel) generationPanel).getVerticesCountToShow());
      
        controller.setAktualizator(graphActualizer);
        startThreads();
    }

    /**
     * Starts actualizers and main program.
     */
    private void startThreads() {
        graphActualizer.start();

        controller.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

     
        simulationPanel = new javax.swing.JPanel();
       
        
       
    
        
       // tabChoosePanel.setPreferredSize(new Dimension(350,768));
      
        mainGraphPanel = new javax.swing.JPanel();
        tabChoosePanel = new javax.swing.JTabbedPane();
  
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        
        simulationMenu = new javax.swing.JMenu();
        graphMenu = new javax.swing.JMenu();
        resultsMenu = new javax.swing.JMenu();
        

    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulation");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366,768));

        simulationPanel.setBorder(null);
        simulationPanel.setBackground(Color.gray);
        simulationPanel.setForeground(Color.white);
        simulationPanel.setMaximumSize(new java.awt.Dimension(350, 768));
        simulationPanel.setPreferredSize(new java.awt.Dimension(340, 768));

        Font font = new JLabel().getFont();
        
        
      

        
      
        
     
        
        
        mainGraphPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 254, 254)));
        mainGraphPanel.setMaximumSize(new java.awt.Dimension(400, 1400));
        mainGraphPanel.setMinimumSize(new java.awt.Dimension(300, 300));
        mainGraphPanel.setLayout(new java.awt.BorderLayout());
 
      
        
        tabChoosePanel.setBackground(Color.LIGHT_GRAY);
        tabChoosePanel.setForeground(Color.white);
     
        tabChoosePanel.addChangeListener(new javax.swing.event.ChangeListener() {
        	
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabChoosePanelStateChanged(evt);
                
            }
        });

       

     

        setJMenuBar(menuBar);


        graphPanel = new GraphPanel();
        graphPanel.setBackground(Color.white);
        mainGraphPanel.add(graphPanel);
 
          getContentPane().add(tabChoosePanel,BorderLayout.WEST);
          getContentPane().add(mainGraphPanel,BorderLayout.CENTER);
          getContentPane().setBackground(Color.white);
          
          
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents







    private void tabChoosePanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabChoosePanelStateChanged
      

    }//GEN-LAST:event_tabChoosePanelStateChanged

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed



    /**
     * Actualizes controller with data collected from GUI.
     */
    private void actualizeController() {

    }



    public static javax.swing.JButton createCustomButton(String text) {
    	javax.swing.JButton button = new javax.swing.JButton(text);
    	  button.setForeground(Color.WHITE);
    	  button.setBackground(Color.DARK_GRAY);
    	 // button.setBorder(null);
    	  button.setOpaque(true);
    	  button.setText(text);
    	  return button;
    	}



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        UIManager.put("TabbedPane.borderHightlightColor", java.awt.Color.gray); 
        UIManager.put("TabbedPane.darkShadow", java.awt.Color.gray); 
        UIManager.put("TabbedPane.light", java.awt.Color.gray);
        UIManager.put("TabbedPane.selectHighlight", java.awt.Color.gray);
        UIManager.put("TabbedPane.darkShadow", java.awt.Color.gray);
        UIManager.put("TabbedPane.focus", java.awt.Color.gray);
        UIManager.getDefaults().put("TabbedPane.lightHighlight", Color.gray);
        UIManager.getDefaults().put("TabbedPane.selectHighlight", Color.gray);
        UIManager.put("TabbedPane.selected",Color.gray);
        UIManager.put("nimbusBase", Color.GRAY);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("Panel.background", java.awt.Color.gray);
        UIManager.put("Label.foreground", java.awt.Color.white);
        UIManager.put("TitledBorder.titleColor", java.awt.Color.white);
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
           

                new SimulationGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;


  
    private javax.swing.JPanel controlPanel;

    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu simulationMenu;
    private javax.swing.JMenu graphMenu;
    private javax.swing.JMenu resultsMenu;

    private javax.swing.JMenu helpMenu;
    

    private GraphPanel graphPanel;
    private javax.swing.JPanel mainGraphPanel;
    private javax.swing.JMenuBar menuBar;

    private javax.swing.JPanel simulationPanel;
    private javax.swing.JPanel generationPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JTabbedPane tabChoosePanel;
    // End of variables declaration//GEN-END:variables
}
