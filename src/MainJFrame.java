import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

 

public class MainJFrame extends JFrame{
	
	
	private DrawPanel drawPanel = new DrawPanel();
	
	public MainJFrame() {
		setTitle("Algoritmos em Grafos - Universidade Federal do Ceará");
	    setSize(750,500);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container c = this.getContentPane();
	    c.setLayout(new BorderLayout());
	    
	    //south
	    JPanel panelSouth = new JPanel();
	    panelSouth.setLayout(new BorderLayout());
	    final JTextField arg0 = new JTextField(20);
	    arg0.setText("teste.txt");
	    final JTextField arg1 = new JTextField(20);
	    arg1.setText("v1");
	    final JTextField arg2 = new JTextField(20);
	    arg2.setText("v4");
	    final JTextField res = new JTextField(50);
	    panelSouth.add(arg0,BorderLayout.WEST);
	    panelSouth.add(arg1,BorderLayout.CENTER);
	    panelSouth.add(arg2,BorderLayout.EAST);
	    panelSouth.add(res,BorderLayout.SOUTH);
	    
	    //adding
	    c.add(panelSouth,BorderLayout.SOUTH);
	    c.add(drawPanel,BorderLayout.CENTER);
	    
	    JButton okButton = new JButton("OK");
	    c.add(okButton,BorderLayout.NORTH);
	    okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String a0 = arg0.getText();
				String a1 = arg1.getText();
				String a2 = arg2.getText();
				
				Grafo teste = new Grafo();
				teste.setVertices(LerDoArquivo.lerGrafo(a0));
				Vertice i1 = new Vertice();
				Vertice i2 = new Vertice();
				i1 = teste.encontrarVertice(a1);
				i2 = teste.encontrarVertice(a2);

				List<Vertice> resultado = new ArrayList<Vertice>();
				Dijkstra algoritmo = new Dijkstra();
				resultado = algoritmo.encontrarMenorCaminhoDijkstra(teste, i1, i2);
				
				drawPanel.draw(teste.getVertices());
				drawPanel.updateUI();

				System.out.println("Esse é o menor caminho feito pelo algoritmo:"
						+ resultado);
				res.setText("Esse é o menor caminho feito pelo algoritmo:"
						+ resultado);
			}
		});
	    
	    
	    
	    this.setVisible(true);
	    this.setResizable(false);
	}
	
	public static void main(String[] args) {
		new MainJFrame();
	}

}