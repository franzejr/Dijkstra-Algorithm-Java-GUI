/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* ----------------------
 * JGraphAdapterDemo.java
 * ----------------------
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh
 * Contributor(s):   -
 *
 * $Id: JGraphAdapterDemo.java 725 2010-11-26 01:24:28Z perfecthash $
 *
 * Changes
 * -------
 * 03-Aug-2003 : Initial revision (BN);
 * 07-Nov-2003 : Adaptation to JGraph 3.0 (BN);
 *
 */
 

import java.awt.*;
import java.awt.geom.*;
import java.util.List;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;

 

/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 * @since Aug 3, 2003
 */
public class DrawPanel
    extends JPanel
{
    //~ Static fields/initializers ---------------------------------------------

    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    //~ Instance fields --------------------------------------------------------

    //
    private JGraphModelAdapter<String, MyEdge> jgAdapter;

    //~ Methods ----------------------------------------------------------------

    
     
    /**
     * {@inheritDoc}
     */
    public DrawPanel(){
        this.setBackground(Color.white);
        this.setSize(400,400);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
     
    public void draw(List<Vertice> vertices){
    	// create a JGraphT graph
        ListenableGraph<String, MyEdge> g =
            new ListenableDirectedMultigraph<String, MyEdge>(
                MyEdge.class);

        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<String, MyEdge>(g);

        JGraph jgraph = new JGraph(jgAdapter);
        //jgraph.setBackground(Color.white);
        adjustDisplaySettings(jgraph);
        
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jgraph,BorderLayout.CENTER);
        
        
        resize(DEFAULT_SIZE);
        
        for(Vertice v:vertices){
        	g.addVertex(v.getDescricao().trim());
        }
        
        for(Vertice v1:vertices){
        	for(Aresta a: v1.getArestas())
        		g.addEdge(v1.getDescricao().trim(), a.getDestino().getDescricao().trim(), new MyEdge(a.getPeso()+""));
        }
        
        
        
        int size = vertices.size();
        int i = 0;
        int x = 20;
        int y = 20;
        
        for(Vertice v: vertices){
            
            positionVertexAt(v.getDescricao().trim(),x,y);
            x+=150;
            if(i==size/2){
            	x = 20;
            	y+=200;
            }
            	
            i++;
        }
        	

         

        // that's all there is to it!...


    }

    private void adjustDisplaySettings(JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = Color.white;
 

        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked") // FIXME hb 28-nov-05: See FIXME below
    private void positionVertexAt(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
            new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * a listenable directed multigraph that allows loops and parallel edges.
     */
    private static class ListenableDirectedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
        implements DirectedGraph<V, E>
    {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedMultigraph<V, E>(edgeClass));
        }
    }
    
    class MyEdge extends DefaultEdge{
    	
    	private String label;
    	
    	public MyEdge(String label) {
			this.label = label;
		}
    	
    	@Override
    	public String toString() {
    		return label;
    	}
    }
}

// End JGraphAdapterDemo.java