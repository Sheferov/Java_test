public class Break_Continue {
    public static void main(String[]args){
        for(int x = 5; x<=55;x++){
            if(x%2==0){
                continue;
            }
            System.out.println("Нечетное число"+x);
        }
    }
}
