// class Solution {
//     /**
//     We still use unionFind to solve this question, but it has two kind of cases
//     Case 1: No duplicate parents, return the first edge that creates the loop --> Same as 684
//     Case 2: A node has two parents {u1, u2}
//         2.1: return the second edge that creates duplicate parents (no loop). Example:[[1,2], [1,3], [2,3]]
//                       1
//                      / \
//                     v   v
//                     2-->3    Node 3 has two parents: 1 and 2. Remove any one of the edge satisfy the question. But we need to remove the one that occurs the last.
//         2.2: return edge {u1, v} or edge {u2, v} that creates the loop. Example:[[2,1],[3,1],[4,2],[1,4]]
//                     2--> 1 <-- 3
//                     /\   |
//                      \   v
//                       \- 4    Node 1 has two parents. We have to remove either {2,1} or {3,1}. {2,1} is the one that creates the loop. So we remove {2,1}
   
// 	So our algorithm uses two loops: First loop to detect if there is any duplicate parents.
// 	2nd loop to detect if there is any loop in the graph.
//     */
//     class UnionFind{
//         int[] parents;
//         public UnionFind(int N){
//             parents = new int[N];
//             for(int i=0; i<N; i++)
//                 parents[i] = i;
//         }
//         public void union(int x, int y){
//             parents[find(x)] = find(y);
//         }
//         public int find(int x){
//             if(x!=parents[x])
//                 parents[x] = find(parents[x]);
//             return parents[x];
//         }
//     }
//     public int[] findRedundantDirectedConnection(int[][] edges) {
//         int [] edge1 = new int [2];
//         int [] edge2 = new int [2]; /*Possible two edges(Two parents) in cases 2. */
//         int [] parent = new int [edges.length + 1];
//         UnionFind uf = new UnionFind(edges.length + 1);
//         for(int i=0; i<edges.length; i++){ /*First loop to detect if there is duplicate parents*/
//             int nodeU = edges[i][0];
//             int nodeV = edges[i][1];
//             if(parent[nodeV] > 0){  /* there is duplicate parents*/
//                 edge1 = new int[]{parent[nodeV], nodeV}; /*Add previous/first edge*/
//                 edge2 = new int[]{nodeU, nodeV};/*Add 2nd edge*/
//                 edges[i][0] = -1;
//                 edges[i][1] = -1;/*Delete the 2nd edge first*/
//             }
//             parent[nodeV] = nodeU; 
//         }
//         for(int i=0; i<edges.length; i++){
//             int nodeU = edges[i][0];
//             int nodeV = edges[i][1];
//             if(nodeU<0 || nodeV<0)
//                 continue; /*This is for the deleted edge we have done in first loop.*/
//             int rootU = uf.find(nodeU);
//             int rootV = uf.find(nodeV);
//             if(rootU==rootV) /*Since we already deleted the 2nd edge, then it must be edge1. If edge1 is not assigned with any values.*/
//                 return edge1[0]==0? new int[]{nodeU, nodeV} : edge1;/*Then it means there is no duplicate parents. So case1: return current detected edge.*/
//             uf.union(nodeU, nodeV);
//         }
//         return edge2; /*If reached here, it means there is no loop detected, otherwise it would return at 2nd loop in our code. So case 2.1, return 2nd edge.*/
//     }
// }




///////////////////////new apporach Mohit Bhaiya//////////////////////////



class Solution {
    class UnionFind{
        int par[];
        int rank[];
        
        
        UnionFind(int vtces){
            par = new int[vtces+1];
            rank = new int[vtces+1];
            for(int i = 1 ; i <= vtces ; i++){
                par[i] = i;
            }
        }
        
        void union(int v1,int v2){
            int rt1 = find(v1);
            int rt2 = find(v2);
            if(rt1 != rt2){
                int rank1 = rank[v1];
                int rank2 = rank[v2];

                if(rank1 < rank2){
                    par[rt1] = rt2;
                }else if(rank1 > rank2){
                    par[rt2] = rt1;
                }else{
                    par[rt2] = rt1;
                    rank[rt1]++;
                }
            }
        }
        
        int find(int v){
            if(par[v] == v){
                return v;
            }
            return par[v] = find(par[v]);
        }
        
        boolean isConnected(int v1,int v2){
            return find(v1) == find(v2);
        }
    
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int indeg[] = new int[n+1];
        Arrays.fill(indeg,-1);
        int bl1 = -1;
        int bl2 = -1;
        
        for(int i = 0 ; i < edges.length ; i++){
            int edge[] = edges[i];
            int u = edge[0];
            int v = edge[1];
            if(indeg[v] == -1){
                indeg[v] = i;
            }else{
                bl1 = i; // last incoming edge
                bl2 = indeg[v]; // second last incoming edge
            }
        }
        
        // union find
        UnionFind uf = new UnionFind(n);
        for(int i = 0 ; i < edges.length ; i++){
            int edge[] = edges[i];
            int u = edge[0];
            int v = edge[1];
            if(bl1 == -1){
                if(uf.isConnected(u,v)){
                    return edge;
                }else{
                    uf.union(u,v);
                }
            }else{
                if(i != bl1){
                    if(uf.isConnected(u,v)){
                        return edges[bl2];
                    }else{
                        uf.union(u,v);
                    }
                }
            }
        }
        
        return edges[bl1];
    }
}





















