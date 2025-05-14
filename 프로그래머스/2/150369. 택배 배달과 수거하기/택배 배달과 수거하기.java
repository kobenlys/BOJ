import java.util.*;

class Solution {
    
    public static class Truck{
        int deliverBox, pickupBox;
        public Truck(int deliverBox, int pickupBox){
            this.deliverBox = deliverBox; 
            this.pickupBox = pickupBox;
        }
        
        public void clear(){
            this.deliverBox = 0; 
            this.pickupBox = 0;
        }
        
    }
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        PriorityQueue<Integer> first = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> second = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i < n; i++){
            
            int size = deliveries[i];
            
            for(int j=0; j<size; j++){
                first.add(i+1);
            }
            
            size = pickups[i];
            for(int j=0; j<size; j++){
                second.add(i+1);
            }
        }
        
        Truck truck = new Truck(0,0);
        
        
        while(!first.isEmpty() || !second.isEmpty()){
            int size = first.size();
            
            truck.deliverBox = Math.min(cap, size);
            size = truck.deliverBox;
            int max1 = 0;
            for(int j=0; j<size; j++){
                max1 = Math.max(max1, first.poll());
            }
            
            int max2 = 0;
            size = second.size();
            truck.pickupBox = Math.min(cap, size);
            size = truck.pickupBox;
            
            for(int j=0; j<size; j++){
                max2 = Math.max(max2, second.poll());
            }
            
            //System.out.println(max1 + " "+ max2);
            answer += Math.max(max1, max2) * 2;
            
            truck.clear();
            
        }
        
        
        return answer;
    }
}