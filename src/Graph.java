
public class Graph {
    private int maxN = 1000;
    private int[][] matr;
    Vertex[] vertexList; //список вершин
    private int countV; //кол-во вершин
    private Stack<Integer> stack;
    private Queue<Integer> queue ;
    private boolean isDirected;

    public int getCountOfVertices() {
        return countV;
    }

    public Graph(boolean isDirected){
        this.stack = new Stack<>(maxN);
        this.queue = new Queue<>(maxN);
        this.isDirected = isDirected;
        vertexList = new Vertex[maxN];
        matr = new int[maxN][maxN];
        countV = 0;

    }
    public void addVertex(char name) { //задает узлы
        for (int i = 0; i < countV; i++) {
            if (vertexList[i].name == name) {
                System.out.println(" ");
                System.out.println("Невозможно добавить вершину, т.к она уже существует. Придумайте другое обозначение.");
                return;
            }
        }
        if (countV > maxN){
            System.out.println("Невозможно добавить вершину, т.к превышено их количество");
            return;
        }
        vertexList[countV] = new Vertex(name);
        System.out.println("Вершина успешно добавлена.");
        countV++;
    }
    public void addEdge(int start, int end, int weight) { //задает ребра
        if (start < 0 || start >= countV || end < 0 || end >= countV){
            System.out.println("Вы ввели не верную вершину.");
            return;
        }
        if (!isDirected){
            matr[start][end] = weight;

            matr[end][start] = weight;
        }
        else{
            matr[start][end] = weight;

            matr[end][start] = 0;
        }

    }
    public int check(int v){ //есть ли смежные вершины и непосещенные
        for (int i = 0; i < countV; i++){
            if (matr[v][i] != 0 && vertexList[i].isVisited == false){
                return i;
            }
        } return -1;
    }
    public void depthFirstPaths(int index){
        System.out.println(vertexList[index].name);
        vertexList[index].isVisited = true;
        stack.push(index);
        while (!stack.isEmpty()){
            int neighbor = check(stack.peek());
            if (neighbor == -1){
                stack.pop();

            }
            else{
                System.out.println(vertexList[neighbor].name);
                vertexList[neighbor].isVisited = true;
                stack.push(neighbor);
            }
        }
    }
    public void removeVertex(int v){
        if (v < 0 || v >= countV){
            return;
        }
        for (int i = v; i < countV-1; i++){
            vertexList[i] = vertexList[i+1];

        } vertexList[countV-1] = null;
        for (int i = v; i < countV - 1; i++){
            for (int j = 0; j < countV; j++){
                matr[i][j] = matr[i+1][j];
            }
        }
        for (int j = v; j < countV - 1; j++){
            for (int i = 0; i < countV; i++){
                matr[i][j] = matr[i][j+1];
            }
        }
        countV --;
    }
    public void removeEdge(int V , int v ) {
        if (V < 0 || v < 0 || V >= countV || v >= countV){
            System.out.println("Вы ввели неверную вершину.");
            return;
        }
        matr[V][v] = 0;
        if (!isDirected){
            matr[V][v] = 0;
            matr[v][V] = 0;
        }
    }
    public VertexList<Vertex> getAdjacent(int v){
        VertexList<Vertex> adjacent = new VertexList<>();
        if (v < 0 || v >= countV){
                System.out.println("Данной вершины нет в графе.");
                return adjacent;
        }
        for (int i = 0; i < countV; i++){
            if (matr[v][i] != 0){
                adjacent.add(vertexList[i]);
            }
        }
        return adjacent;
    }
    public void BreadthFirstSearch(int index){
        for (int i = 0; i < countV; i++){
            vertexList[i].isVisited = false;
        }
        System.out.println(vertexList[index].name);
        vertexList[index].isVisited = true;
        queue.Enqueue(index);

        while (!queue.isEmpty()) {
            int neighbor = check(queue.peek());
            if (neighbor == -1) {
                queue.Dequeue();
            }else{
                System.out.println(vertexList[neighbor].name);
                vertexList[neighbor].isVisited = true;
                queue.Enqueue(neighbor);
            }
        }
    }
    public void showVerteces(){
        if (countV == 0){
            System.out.println("Список пуст, т.к вершины отсутствуют.");
            return;
        }
        else {
            System.out.println("Текущие вершины графа: ");
            for (int i = 0; i < countV; i ++) {
                System.out.print(vertexList[i].name + ",");
            }
            System.out.println(" ");
        }
    }
    public int findindex(char name){
        for (int i = 0; i < countV; i++){
            if (vertexList[i].name == name){
                return i;
            }
        }
        return -1;
    }
    public boolean isEge(int start, int end) { //существует ли ребро
        if (matr[start][end] != 0) {
            System.out.println("Невозможно добавить ребро, т.к это ребро уже существует");
            return true;
        }
        if (isDirected && matr[end][start] != 0) {
            System.out.println("Вы создали ориентированный граф, поэтому нельзя задать ребро в обе стороны.");
            return true;
        }

        System.out.println("Между данными вершинами еще нет ребра, можете смело его добавлять.");
        return false;

    }

}
