import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice>{
        
        private String descricao;
        private int distancia;
        private boolean visitado = false;
        private Vertice pai;
        private List<Aresta> arestas = new ArrayList<Aresta>();
        private List<Vertice> vizinhos = new ArrayList<Vertice>();
        
        public void setDescricao(String nome){
                
                this.descricao = nome;
        }
        
        public String getDescricao(){
                
                return descricao;
                
        }
        
        public void visitar (){
                
                this.visitado = true;
        }
        
        public boolean verificarVisita(){
                
                return visitado;
        }
        
        public void setDistancia(int distancia){
                
                this.distancia = distancia;
        }
        
        public int getDistancia(){
                
                return this.distancia;
        }
        
        public void setPai(Vertice pai){
                
                this.pai = pai;
        }
        
        public Vertice getPai(){
                
                return this.pai;
        }

        public void setVizinhos(List<Vertice> vizinhos) {
                
                this.vizinhos.addAll(vizinhos);
                                
        }
        
        public List<Vertice> getVizinhos(){
                
                return this.vizinhos;
        }
        
        public void setArestas(List <Aresta> arestas){
                
                this.arestas.addAll(arestas);
                
        }
        
        public List<Aresta> getArestas() {
                
                return arestas;
        }

        public int compareTo(Vertice vertice) {
                  if(this.getDistancia() < vertice.getDistancia()) return -1;
          else if(this.getDistancia() == vertice.getDistancia()) return 0;
          
          return 1;

                
        }
        
        @Override
        public boolean equals(Object obj) {
                if(obj instanceof Vertice){
                        Vertice vRef = (Vertice) obj;
                        if(this.getDescricao().equals(vRef.getDescricao())) return true;
                }
                return false;
        }
        
        @Override
        public String toString() {
                String s = " ";
                s+= this.getDescricao();
                return s;
        }
        
        
        
        
}