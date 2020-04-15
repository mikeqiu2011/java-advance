import com.mosh.generics.GenericList;

public class Main {

    public static void main(String[] args) {

        GenericList<String> genericList = new GenericList<>();

        genericList.add("a");
        genericList.add("b");


        for (String item : genericList)
            System.out.println(item);

    }
}
