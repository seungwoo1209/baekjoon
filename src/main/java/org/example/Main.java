package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.MAX_VALUE;

class Main {

    static int N;
    static int M;
    static boolean[][] isThereWall;
    static int[][][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isThereWall = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            char[] arr = line.toCharArray();
            for (int j = 1; j <= M; j++) {
                isThereWall[i][j] = Character.getNumericValue(arr[j - 1]) == 1;
            }
        }
        // 0: 벽깨기 안쓰고 1: 벽깨기 쓰고
        dist = new int[2][N + 1][M + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < M + 1; k++) {
                    dist[i][j][k] = MAX_VALUE;
                }
            }
        }
        ArrayDeque<Coord> queue = new ArrayDeque<>();
        dist[0][1][1] = 1;

        queue.add(new Coord(1, 1));

        while (!queue.isEmpty()) {
            Coord coord = queue.removeFirst();
            int row = coord.row;
            int col = coord.col;

            // 현재 좌표에 벽이 있음
            if(isThereWall[row][col]){
                // 위쪽으로 갈 수 있고, 여기에 벽이 없고
                if (row >= 2 && !isThereWall[row-1][col]) {
                    int d = dist[1][row][col] + 1;

                    //벽 안 뚫고 여길 갈 수 있으면
                    if(dist[0][row-1][col] < MAX_VALUE){
                        // 근데 현재 좌표 거쳐서(= 벽뚫기 씀) 가는 경우가 더 이득이면
                        if(d < dist[0][row-1][col]){
                            dist[1][row-1][col] = d;
                            queue.add(new Coord(row-1,col));
                        }
                    } else { // 여기도 무조건 벽 뚫고 가야 하는 경우인데 현재 좌표 거처가는게 더 이득이면
                        if(d < dist[1][row-1][col]){
                            dist[1][row-1][col] = d;
                            queue.add(new Coord(row-1,col));
                        }
                    }
                }
                // 왼쪽으로 갈 수 있고, 여기에 벽이 없고
                if (col >= 2 && !isThereWall[row][col-1]) {
                    int d = dist[1][row][col] + 1;

                    if(dist[0][row][col-1] < MAX_VALUE){
                        if(d < dist[0][row][col-1]){
                            dist[1][row][col-1] = d;
                            queue.add(new Coord(row,col-1));
                        }
                    } else {
                        if(d < dist[1][row][col-1]){
                            dist[1][row][col-1] = d;
                            queue.add(new Coord(row,col-1));
                        }
                    }
                }
                // 아래쪽으로 갈 수 있고, 여기에 벽이 없고
                if (row < N && !isThereWall[row+1][col]) {
                    int d = dist[1][row][col] + 1;

                    if(dist[0][row-1][col] < MAX_VALUE){
                        if(d < dist[0][row+1][col]){
                            dist[1][row+1][col] = d;
                            queue.add(new Coord(row+1,col));
                        }
                    } else {
                        if(d < dist[1][row+1][col]){
                            dist[1][row+1][col] = d;
                            queue.add(new Coord(row+1,col));
                        }
                    }
                }
                // 오른쪽으로 갈 수 있고, 여기에 벽이 없고
                if (col < M && !isThereWall[row][col+1]) {
                    int d = dist[1][row][col] + 1;

                    if(dist[0][row][col+1] < MAX_VALUE){
                        if(d < dist[0][row][col+1]){
                            dist[1][row][col+1] = d;
                            queue.add(new Coord(row,col+1));
                        }
                    } else {
                        if(d < dist[1][row][col+1]){
                            dist[1][row][col+1] = d;
                            queue.add(new Coord(row,col+1));
                        }
                    }
                }
            } else {
                // 현재 좌표로 벽 안쓰곤 못가는 경우
                if(dist[0][row][col] == MAX_VALUE){
                    // 위쪽으로 갈 수 있고, 여기에 벽이 없고
                    if (row >= 2 && !isThereWall[row-1][col]) {
                        int d = dist[1][row][col] + 1;

                        //벽 안 뚫고 여길 갈 수 있으면
                        if(dist[0][row-1][col] < MAX_VALUE){
                            // 근데 현재 좌표 거쳐서(= 벽뚫기 씀) 가는 경우가 더 이득이면
                            if(d < dist[0][row-1][col]){
                                dist[1][row-1][col] = d;
                                queue.add(new Coord(row-1,col));
                            }
                        } else { // 여기도 무조건 벽 뚫고 가야 하는 경우인데 현재 좌표 거처가는게 더 이득이면
                            if(d < dist[1][row-1][col]){
                                dist[1][row-1][col] = d;
                                queue.add(new Coord(row-1,col));
                            }
                        }
                    }
                    // 왼쪽으로 갈 수 있고, 여기에 벽이 없고
                    if (col >= 2 && !isThereWall[row][col-1]) {
                        int d = dist[1][row][col] + 1;

                        if(dist[0][row][col-1] < MAX_VALUE){
                            if(d < dist[0][row][col-1]){
                                dist[1][row][col-1] = d;
                                queue.add(new Coord(row,col-1));
                            }
                        } else {
                            if(d < dist[1][row][col-1]){
                                dist[1][row][col-1] = d;
                                queue.add(new Coord(row,col-1));
                            }
                        }
                    }
                    // 아래쪽으로 갈 수 있고, 여기에 벽이 없고
                    if (row < N && !isThereWall[row+1][col]) {
                        int d = dist[1][row][col] + 1;

                        if(dist[0][row+1][col] < MAX_VALUE){
                            if(d < dist[0][row+1][col]){
                                dist[1][row+1][col] = d;
                                queue.add(new Coord(row+1,col));
                            }
                        } else {
                            if(d < dist[1][row+1][col]){
                                dist[1][row+1][col] = d;
                                queue.add(new Coord(row+1,col));
                            }
                        }
                    }
                    // 오른쪽으로 갈 수 있고, 여기에 벽이 없고
                    if (col < M && !isThereWall[row][col+1]) {
                        int d = dist[1][row][col] + 1;

                        if(dist[0][row][col+1] < MAX_VALUE){
                            if(d < dist[0][row][col+1]){
                                dist[1][row][col+1] = d;
                                queue.add(new Coord(row,col+1));
                            }
                        } else {
                            if(d < dist[1][row][col+1]){
                                dist[1][row][col+1] = d;
                                queue.add(new Coord(row,col+1));
                            }
                        }
                    }
                } else {
                    //벽 안써도 현재좌표로 갈 수 있는 경우

                    //안쓴게 현재 나을 경우
                    if(dist[0][row][col] > dist[1][row][col] ){
                        // 위쪽으로 갈 수 있고, 여기에 벽이 없고
                        if (row >= 2) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row-1][col]){
                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row-1][col]){
                                    dist[0][row-1][col] = d;
                                    queue.add(new Coord(row-1,col));
                                }
                            } else {
                                if(d < dist[1][row-1][col]){
                                    dist[1][row-1][col] = d;
                                    queue.add(new Coord(row-1,col));
                                }
                            }

                        }
                        if (col >= 2) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row][col-1]){
                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row][col-1]){
                                    dist[0][row][col-1] = d;
                                    queue.add(new Coord(row,col-1));
                                }
                            } else {
                                if(d < dist[1][row][col-1]){
                                    dist[1][row][col-1] = d;
                                    queue.add(new Coord(row,col-1));
                                }
                            }

                        }
                        if (row < N) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row+1][col]){
                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row+1][col]){
                                    dist[0][row+1][col] = d;
                                    queue.add(new Coord(row+1,col));
                                }
                            } else {
                                if(d < dist[1][row+1][col]){
                                    dist[1][row+1][col] = d;
                                    queue.add(new Coord(row+1,col));
                                }
                            }

                        }
                        if (col < M) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row][col+1]){
                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row][col+1]){
                                    dist[0][row][col+1] = d;
                                    queue.add(new Coord(row,col+1));
                                }
                            } else {
                                if(d < dist[1][row][col+1]){
                                    dist[1][row][col+1] = d;
                                    queue.add(new Coord(row,col+1));
                                }
                            }

                        }

                    } else {
                        //벽 쓴게 현재 나을 경우

                        if (row >= 2) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row-1][col]){
                                boolean flag = false;

                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row-1][col]){
                                    dist[0][row-1][col] = d;
                                    flag = true;
                                }

                                if(dist[1][row][col] != MAX_VALUE && dist[1][row][col] + 1 < dist[1][row-1][col]){
                                    dist[1][row-1][col] = dist[1][row][col] + 1;
                                    flag = true;
                                }

                                if(flag){
                                    queue.add(new Coord(row-1,col));
                                }
                            } else {
                                if(d < dist[1][row-1][col]){
                                    dist[1][row-1][col] = d;
                                    queue.add(new Coord(row-1,col));
                                }
                            }

                        }
                        if (col >= 2) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row][col-1]){
                                boolean flag = false;

                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row][col-1]){
                                    dist[0][row][col-1] = d;
                                    flag = true;
                                }

                                if(dist[1][row][col] != MAX_VALUE && dist[1][row][col] + 1 < dist[1][row][col-1]){
                                    dist[1][row][col-1] = dist[1][row][col] + 1;
                                    flag = true;
                                }

                                if(flag){
                                    queue.add(new Coord(row,col-1));
                                }
                            } else {
                                if(d < dist[1][row][col-1]){
                                    dist[1][row][col-1] = d;
                                    queue.add(new Coord(row,col-1));
                                }
                            }

                        }
                        if (row < N) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row+1][col]){
                                boolean flag = false;

                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row+1][col]){
                                    dist[0][row+1][col] = d;
                                    flag = true;
                                }

                                if(dist[1][row][col] != MAX_VALUE && dist[1][row][col] + 1 < dist[1][row+1][col]){
                                    dist[1][row+1][col] = dist[1][row][col] + 1;
                                    flag = true;
                                }

                                if(flag){
                                    queue.add(new Coord(row+1,col));
                                }
                            } else {
                                if(d < dist[1][row+1][col]){
                                    dist[1][row+1][col] = d;
                                    queue.add(new Coord(row+1,col));
                                }
                            }

                        }
                        if (col < M) {
                            int d = dist[0][row][col] + 1;
                            if(!isThereWall[row][col+1]){
                                boolean flag = false;

                                //벽 안 뚫고 여길 갈 수 있으면
                                if(d < dist[0][row][col+1]){
                                    dist[0][row][col+1] = d;
                                    flag = true;
                                }

                                if(dist[1][row][col] != MAX_VALUE && dist[1][row][col] + 1 < dist[1][row][col+1]){
                                    dist[1][row][col+1] = dist[1][row][col] + 1;
                                    flag = true;
                                }

                                if(flag){
                                    queue.add(new Coord(row,col+1));
                                }
                            } else {
                                if(d < dist[1][row][col+1]){
                                    dist[1][row][col+1] = d;
                                    queue.add(new Coord(row,col+1));
                                }
                            }

                        }
                    }
                }
            }
        }

        int result = Math.min(dist[0][N][M],dist[1][N][M]);
        if(result == MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

}

class Coord {
    public int row;
    public int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
