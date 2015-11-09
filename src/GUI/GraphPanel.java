/*
 * authors: Dominik Juraszek, Konrad Chmiel, Sebastian Brandys
 */
package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;

import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import graph.GraphVisualisation;
import graph.LayoutType;

public class GraphPanel extends JPanel {

    private VisualizationViewer<Integer, String> vv; // visualization viewer of graph
    private Layout<Integer, String> actualGrLayout = null; // displayed layout
    private Graph<Integer, String> graph; // graph
    private LayoutType layoutType = LayoutType.CIRCLE; // actually chosen layout of a graph


 
    /**
     * Setter
     *
     * @param graph
     */
    public void setGraph(Graph<Integer, String> graph) {
        this.graph = graph;
    }


    /**
     * Setter of visoalization viewer
     *
     * @param vv
     */
    public void setVv(VisualizationViewer<Integer, String> vv) {
        this.vv = vv;
    }

    /**
     * Getter of visualization viewer
     *
     * @return visoalization viewer
     */
    public VisualizationViewer<Integer, String> getVv() {
        return vv;
    }

    /**
     * Constructor.
     */
    public GraphPanel() {
        this.vv = null;
        setBackground(Color.white);
        setDoubleBuffered(true);
    }

    /**
     * Sets new layout type and repaints view if layout changed.
     *
     * @param layoutType - type of layout
     */
    public void setLayoutType(LayoutType layoutType) {
        if (this.layoutType != layoutType) {
            this.layoutType = layoutType;
            if (graph != null) {
                add(actualizeVisualization( true));
                if (getComponentCount() > 1) {
                    remove(0);
                }
                repaint();
            }
        }
    }

    @Override
    public void repaint() {
        super.repaint();
        if (graph != null) {
            actualizeVisualization( false);
            validate();
        }
    }

    /**
     * Function actualizes graph on the screen
     *
     * @param g - new graph
     */
    public final void displayNewGraph(Graph<Integer, String> g) {
        this.graph = g;
        add(actualizeVisualization( true));
        if (getComponentCount() > 1) {
            remove(0);
        }
        repaint();
    }

    /**
     * Displays given individual.
     *
     * @param individual
     */
    public void displayNewBest() {
        add(actualizeVisualization( true));
        if (getComponentCount() > 1) {
            remove(0);
        }
        repaint();
    }

    /**
     * Creates visualization of a graph with actualIndividual one from population
     *
     * @param bestOne - actualIndividual subgraph from population
     * @param changedGraph - set true, if graph is new
     * @return visualization
     */
    public synchronized VisualizationViewer<Integer, String> actualizeVisualization( boolean changedGraph) {
   
        if (changedGraph) {
            actualGrLayout = GraphVisualisation.getLayout(graph, layoutType);
            Dimension dim = new Dimension((int)getSize().getWidth()*3, (int)getSize().getHeight()*3);
            
            vv = new VisualizationViewer<>(actualGrLayout, dim);
            vv.setBackground(Color.WHITE);
            vv.setGraphMouse(new DefaultModalGraphMouse<String, Number>());
        }
        vv.getRenderContext().setVertexDrawPaintTransformer(new VertexDrawing());
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexPainting());
        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgePainting());
        vv.getRenderContext().setEdgeStrokeTransformer(new EdgeThickness());
        return vv;
    }

    /**
     * Paints edges - if it's in a actualIndividual one, it's blue; otherwise black.
     */
    public class EdgePainting implements Transformer<String, Paint> {


        @Override
        public Paint transform(String e) {

            return Color.BLACK;
        }
    }

    /**
     * Sets edge thickness, thick - in actualIndividual one.
     */
    public class EdgeThickness implements Transformer<String, Stroke> {

        protected final Stroke THIN = new BasicStroke(1); // thickness of edge (thin)
        protected final Stroke THICK = new BasicStroke(2); // thickness of edge (thick)



        @Override
        public Stroke transform(String e) {

            return THIN;
        }
    }

    /**
     * Draw black border of vertex.
     */
    public class VertexDrawing implements Transformer<Integer, Paint> {

        @Override
        public Paint transform(Integer v) {
            return Color.BLACK;
        }

    }

    /**
     * Sets color of vertex; yellow - in actualIndividual one; red - otherwise.
     */
    public class VertexPainting implements Transformer<Integer, Paint> {



        @Override
        public Paint transform(Integer i) {

            return Color.RED;
        }

    }
}
