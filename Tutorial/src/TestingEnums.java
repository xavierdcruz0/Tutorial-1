

/**
 * Created by xavi on 11/10/15.
 */
public class TestingEnums {
    public enum Dog{
        GREAT_DANE(1234, 12),
        JACK_RUSSELL(3, 19);

        private int size;
        private int height;

        private Dog(int size, int height){
            this.size = size;
            this.height = height;
        }

        public int[] getSize(){
            int[] arr = new int[2];
            arr[0] = size;
            arr[1] = height;
            return arr;

        }
    }

    public static void main(String[] args){
        int test;
        test = Dog.GREAT_DANE.getSize()[1];
        System.out.println(test);
    }
}
