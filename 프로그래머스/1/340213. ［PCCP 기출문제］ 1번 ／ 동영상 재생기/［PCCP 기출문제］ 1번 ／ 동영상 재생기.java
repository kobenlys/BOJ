class Solution {
    public int getHour(String time){
        return Integer.parseInt(time.substring(0,2));
    }
    
    public int getMinute(String time){
        return Integer.parseInt(time.substring(3,5));
    }
    
    public boolean isInOp(String time, String opStart, String opEnd){
        
        int nowTime = getHour(time)*100 + getMinute(time);
        int start = getHour(opStart)*100 + getMinute(opStart);
        int end = getHour(opEnd)*100 + getMinute(opEnd);
        
        return  start <= nowTime && nowTime <= end;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        // prev : 10초 전으로 이동
        // next : 10초 후로 이동
        // op start, end 사이에 현재 재생위치가 존재한다면 오프닝 끝나는 위치로 이동
        
        boolean isIn = isInOp(pos, op_start, op_end);
        int time = 0;
        
        
        int startOp = getHour(op_start)*100 + getMinute(op_start);
        int endOp = getHour(op_end)*100 + getMinute(op_end);
        
        int endTime = getHour(video_len)*100 + getMinute(video_len);
        
        if(isIn){
            time = getHour(op_end)*100 + getMinute(op_end);
        }else{
            time = getHour(pos)*100 + getMinute(pos);
        }
        
        
        
        
        for(int i=0; i<commands.length; i++){
            String controller =commands[i];
            
            if(controller.equals("next")){
                int hour = time / 100;
                int min = time % 100 + 10;
                
                if(min >= 60){
                    min -= 60;
                    hour += 1;
                }
                time = hour * 100 + min;
                
                if(time > endTime){
                    time = endTime;
                }
                
                
            }else{
                
                int hour = time / 100;
                int min = time % 100 - 10;
                
                if(min < 0){
                    min += 60;
                    hour -= 1;
                }
                //System.out.println(min);
                time = hour * 100 + min;
                
                if(time < 0){
                    time = 0;
                }
            }
            
            
            if(startOp <= time && time <= endOp){
                time = endOp;
            }
            
        }
        
        
        int hour = time / 100;
        int min = time % 100;
        
        if(time >= endTime){
            return video_len;
        }else{
            
            String resHour = String.valueOf(hour);
            String resMin = String.valueOf(min);
            
            if(resHour.length() != 2){
                resHour = "0"+ resHour;
            }
            
            if(resMin.length() != 2){
                resMin = "0" + resMin;
            }
            
            answer = resHour + ":" + resMin;
        }
        
        
        
        return answer;
    }
}