import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        System.out.println("Введите какой граф вы хотели бы создать.");
        System.out.println("Чтобы создать ориентированный, введите 1" + "\n"
                + "Чтобы создать неориентированный граф, введите 2");
        Scanner sc = new Scanner(System.in);
        Graph graph = null;

        if (sc.hasNextInt()) {
            int beginning = sc.nextInt();
            sc.nextLine();

            switch (beginning) {
                case 1:
                    System.out.println("Вами был выбран ориентированный граф, если хотите изменить выбор введите <Изменить>" + "\n"
                            + "Если хотите продолжить, нажмите Enter.");
                    String reverse = sc.nextLine();
                    if (reverse.equalsIgnoreCase("Изменить")) {
                        main(args);
                        return;
                    } else {
                        graph = new Graph(true);
                        System.out.println("Ориентированный граф успешно создан.");
                        featurelist(sc, graph);
                    }
                    break;
                case 2:
                    System.out.println("Вами был выбран неориентированный граф, если хотите изменить выбор введите <Изменить>"
                            + "Если хотите продолжить, нажмите Enter.");
                    String reverse2 = sc.nextLine();
                    if (reverse2.equalsIgnoreCase("Изменить")) {
                        main(args);
                        return;
                    } else {
                        graph = new Graph(false);
                        System.out.println("Неориентированный граф успешно создан.");
                        featurelist(sc, graph);
                    }
                    break;
                default:
                    System.out.println("Ошибка при выборе варианта.");
            }
        }
    }
    public static void featurelist(Scanner sc, Graph graph) {
        while (true){
            System.out.println("Граф поддерживает несколько функций. Выберите цифру от 1 - 10, чтобы работать с ним.");
            System.out.println("""
                    1. Добавить вершину
                    2. Добавить ребро.
                    3. Удалить вершину.
                    4. Удалить ребро.
                    5. Вывод списка смежных вершин.
                    6. Применить к графу обход в глубину
                    7. Применить к графу обход в ширину.
                    """);

            if (sc.hasNextInt()) {
                int variant = sc.nextInt();
                sc.nextLine();
                switch (variant) {
                    case 1:
                        System.out.println("Введите вершины." + "\n"
                                + "(вершина должна быть буквой английского алфавита)");
                        String vertex_m = sc.nextLine();
                        if (vertex_m.length() == 1) {
                            graph.addVertex(vertex_m.charAt(0));
                            graph.showVerteces();
                        } else {
                            System.out.println("Вершину обозначают одной буквой");
                        }
                        break;
                    case 2:
                        System.out.println("Прежде чем ввести грани, решите будет ли ваш граф взвешенный или нет." + "\n"
                                + "Введите Да или Нет: ");

                        String yourchoice = sc.nextLine();
                        boolean weighted = yourchoice.equalsIgnoreCase("Да");

                        while (true) {
                            graph.showVerteces();
                            if (graph.getCountOfVertices() == 0) {
                                System.out.println("Вершин нет, добавление ребра невозможно.");
                                break;
                            }
                            System.out.println("Введите из какой вершины в какую хотите задать ребро");
                            System.out.println("Введите первую вершину: ");
                            char start1 = sc.nextLine().charAt(0);
                            System.out.println("Введите вторую вершину: ");
                            char end1 = sc.nextLine().charAt(0);
                            int startind1 = graph.findindex(start1);
                            int endind1 = graph.findindex(end1);
                            if (startind1 == -1 || endind1 == -1) {
                                System.out.println("Данной вершины нет в графе.");
                                continue;
                            }
                            if (graph.isEge(startind1, endind1)){
                                continue;
                            }
                            if (weighted) {
                                System.out.println("Введите вес ребра");
                                int weight = sc.nextInt();
                                sc.nextLine();
                                graph.addEdge(startind1, endind1, weight);
                                System.out.println("Ребро успешно добавлено.");
                            } else {
                                System.out.println("Т.к граф не взвешенный, то вес ребра равен единице. ");
                                graph.addEdge(startind1, endind1, 1);
                                System.out.println("Ребро успешно добавлено.");
                            }
                            System.out.println("Если не хотите больше добавлять ребра, введите <Да>, в противном случае нажмите Enter.");
                            String choice2 = sc.nextLine();
                            if (choice2.equalsIgnoreCase("Да")) {
                                break;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Введите вершину, которую хотите удалить: ");
                        char vertex1 = sc.nextLine().charAt(0);
                        int vertexind = graph.findindex(vertex1);
                        if (vertexind == -1){
                            System.out.println("Данной вершины нет в графе.");
                        }
                        else{
                            graph.removeVertex(vertexind);
                            System.out.println("Вершина успешно удалена.");
                            graph.showVerteces();
                        }

                        break;
                    case 4:
                        System.out.println("Введите вершины, ребро которых хотите удалить.");
                        graph.showVerteces();
                        System.out.println("Первая вершина: ");
                        char start3= sc.nextLine().charAt(0);
                        System.out.println("Вторая вершина: ");
                        char end3 = sc.nextLine().charAt(0);
                        int startind3 = graph.findindex(start3);
                        int endind3 = graph.findindex(end3);
                        if (startind3 == -1 || endind3 == -1){
                            System.out.println("В графе не существует одна из вершин или обе вершины.");
                        }
                        else {
                            graph.removeEdge(startind3, endind3);
                            System.out.println("Вершина успешно удалена.");
                        }break;
                    case 5:
                        System.out.println("Введите вершину для просмотра смежных вершин: ");
                        char vertex2  = sc.nextLine().charAt(0);
                        int vertexind2 = graph.findindex(vertex2);
                        if (vertexind2 == -1){
                            System.out.println("Вершина не найдена.");
                        }
                        else{
                            VertexList<Vertex> adjacent = graph.getAdjacent(vertexind2);
                            System.out.println("Список смежных вершин: ");
                            for (int i = 0; i < adjacent.size(); i++) {
                                Vertex vertex = adjacent.get(i);
                                System.out.println(vertex.name);
                            }
                        } break;

                    case 6:
                        System.out.println("Введите стартовую вершину, чтобы запустить обход в глубину:");
                        char start4 = sc.nextLine().charAt(0);
                        int startind4 = graph.findindex(start4);
                        if (startind4 == -1){
                            System.out.println("Вершина не найдена");
                        } else {
                            System.out.println("Вывод: ");
                            graph.depthFirstPaths(startind4);
                        }break;

                    case 7:
                        System.out.println("Введите стартовую вершину, чтобы запустить обход в ширину:");
                        char start5 = sc.nextLine().charAt(0);
                        int startind5 = graph.findindex(start5);
                        if (startind5 == -1){
                            System.out.println("Вершина не найдена");
                        } else {
                            System.out.println("Вывод: ");
                            graph.BreadthFirstSearch(startind5);
                        }
                        break;
                    default:
                        System.out.println("Ошибка выбора варианта.");
                }
            } else {
                sc.nextLine();
            }
        }

    }

}






