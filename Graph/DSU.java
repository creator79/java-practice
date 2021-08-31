public class DSU{
    
    public static class UnionFind{
        int []par;
        int []rank;
        public UnionFind(int vtces){
            par = new int[vtces];
            rank = new int[vtces];
            for(int i =  0 ; i < vtces ; i++){
                par[i] = i;
                rank[i] = 1;
            }
        }

        public void union(int vtx1,int vtx2){
            int rootv1 = find(vtx1);
            int rootv2 = find(vtx2);
            int rankv1 = rank[vtx1];
            int rankv2 = rank[vtx2];
            
            if(rankv1 > rankv2){
                par[rootv2] = rootv1;
            }else if(rankv1 < rankv2){
                par[rootv1] = rootv2;
            }else{
                par[rootv2] = rootv1;
                rank[rootv1]++;
            }
        }

        public int find(int vtx){ // returns root
            if(par[vtx] == vtx){
                return vtx;
            }

            return par[vtx] = find(par[vtx]);
        }

        public boolean isConnected(int v1,int v2){ // part of same comp. or not
            return find(v1) == find(v2);
        }
    }
    
    public static void main(String[] args) {
        UnionFind obj = new UnionFind(10);

        obj.union(0, 1);
        obj.union(1, 2);
        obj.union(4, 8);
        obj.union(5, 6);
        obj.union(2, 3);
        obj.union(6, 7);

        System.out.println(obj.isConnected(2, 1));
        System.out.println(obj.isConnected(2, 6));
        System.out.println(obj.isConnected(8, 3));
    }
}