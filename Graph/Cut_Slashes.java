// ?  https://docs.google.com/spreadsheets/d/1RPRa3wMMkj7sjITrnxFs_jzIGuUJ3lTRVP0d7oDPEq4/edit#gid=0

// class Cut_Slashes {
//     private int[] grids;
//     private int[] ranks;
//     private int num;
    
//     private void buildUnionFind(int N) {
//         grids = new int[N * N * 4];
//         ranks = new int[N * N * 4];
//         for (int i = 0; i < grids.length; ++i) {
//             grids[i] = i;
//             ranks[i] = 1;
//         }
//         num = N * N * 4;
//     }
    
//     private int find(int id) {
//         while (id != grids[id]) {
//             grids[id] = grids[grids[id]];
//             id = grids[id];
//         }
//         return id;
//     }
    
//     private void union(int p, int q) {
//         int rootP = find(p);
//         int rootQ = find(q);
        
//         if (rootP == rootQ) return;
        
//         num--;
        
//         if (ranks[rootP] <= ranks[rootQ]) {
//             grids[rootP] = rootQ;
//             ranks[rootQ] += ranks[rootP];
//         } else {
//             grids[rootQ] = rootP;
//             ranks[rootP] += ranks[rootQ];
//         }
//     }
    
//     public int regionsBySlashes(String[] grid) {
//         int N = grid.length;
        
//         buildUnionFind(N);
//         for (int r = 0; r < N; ++r) {
//             for (int c = 0; c < N; ++c) {
//                 process(r, c, N, grid[r].charAt(c));
//             }
//         }
        
//         return num;
//     }
    
//     private int getSubsquare(int r, int c, int N, int ith) {
//         return r * 4 * N + c * 4 + ith;
//     }
    
//     private void process(int r, int c, int N, char square) {
//         // union all 4 sub-squares of current square 
//         int s0 = getSubsquare(r, c, N, 0);
//         int s1 = getSubsquare(r, c, N, 1);
//         int s2 = getSubsquare(r, c, N, 2);
//         int s3 = getSubsquare(r, c, N, 3);
        
//         switch(square) {
//             case ' ': {
//                 union(s0, s1);
//                 union(s1, s2);
//                 union(s2, s3);
//                 break;
//             }
//             case '/': {
//                 union(s0, s1);
//                 union(s2, s3);
//                 break;
//             }
//             default: {
//                 union(s0, s2);
//                 union(s1, s3);
//             }
//         }

        
//         if (r > 0) {
//             union(s0, getSubsquare(r - 1, c, N, 3));
//         }
//         if (r < N - 1) {
//             union(s3, getSubsquare(r + 1, c, N, 0));
//         }
//         if (c > 0) {
//             union(s1, getSubsquare(r, c - 1, N, 2));
//         }
//         if (c < N - 1) {
//             union(s2, getSubsquare(r, c + 1, N, 1));
//         }
//     }
// }



class Solution {
    class UnionFind{
        int par[];
        int rank[];
        int count;
        
        
        UnionFind(int n){
            count = 1;
            par = new int[(n+1)*(n+1)];
            rank = new int[(n+1)*(n+1)];
            for(int i = 0 ; i < (n+1)*(n+1) ; i++){
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
            }else{
                count++;
            }
        }
        int find(int v){
            if(par[v] == v){
                return v;
            }
            return par[v] = find(par[v]);
        }
    }
    public int regionsBySlashes(String[] grid) {
       int n = grid.length;
        
        UnionFind uf = new UnionFind(n);
        for(int r = 0 ; r < n+1 ; r++){
            for(int c = 0 ; c < n+1 ; c++){
                if(r == 0 && c == 0){
                    continue;
                }
                if(r == 0 || c == 0 || r == n || c == n){
                    int point = (r*(n+1))+c;
                    uf.union(0,point);
                }
            }
        }
        for(int i = 0 ; i < grid.length; i++){
            String rowInp = grid[i];
            for(int j = 0 ; j < rowInp.length() ; j++){
                char ch = rowInp.charAt(j);
                if(ch == '/'){
                    int p1 = (i*(n+1))+j+1;
                    int p2 = ( (i+1)*(n+1) )+j;
                    uf.union(p1,p2);
                }else if(ch == '\'){
                    int p1 = (i*(n+1))+j;
                    int p2 = ( (i+1)*(n+1) )+j+1;
                    uf.union(p1,p2);
                }
            }
        }        
        return uf.count;
    }
            }