package practice4;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class BeanUtils {

    public static void main(String[] args) {
        Class cla = Person1.class;
        Class cla2 = Person2.class;
        Object source = null;
        Object target = null;
        try {
             source = cla.newInstance();
             target = cla2.newInstance();
             Method setName = cla.getMethod("setName", String.class);
             Method setAge = cla.getMethod("setAge", Integer.class);
             Method setAddress = cla.getMethod("setAddress", String.class);
             Method setBirthday = cla.getMethod("setBirthday", Date.class);
             setName.invoke(source,"jack");
             setAge.invoke(source,22);
             setBirthday.invoke(source,new Date(2015-11-22));
             setAddress.invoke(source,"临潼比特科技");
             copy(source,target);
           System.out.println(target);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
    /**
     * 对象的属性值拷贝
     * <p>
     * 将source对象中的属性值赋值到target对象中的属性，属性名一样，类型一样
     * <p>
     * example:
     * <p>
     * source:
     * <p>
     * String name;
     * String address;
     * Integer age;
     * Date   birthday;
     * <p>
     * target:
     * String name;
     * String address;
     * String email
     * <p>
     * 结果： source name, address -> target name, address
     *
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) throws NoSuchFieldException, IllegalAccessException {
        //TODO
        Class cla = source.getClass();
        Class cla2 = target.getClass();
        Field[] fields = cla.getDeclaredFields();
        Field[] fields2 = cla2.getDeclaredFields();
        int[] sum = new int[fields2.length];
        for(int i = 0; i < fields.length;i++){
            for(int j = 0; j < fields2.length;j++){
                if(fields[i].getName().equals(fields2[j].getName()) ){
                   Field field= cla.getDeclaredField(fields[i].getName());
                   Field field2 = cla2.getDeclaredField(fields2[j].getName());
                   field.setAccessible(true);
                   field2.setAccessible(true);
                   field2.set(target,field.get(source));
                    break;
                }
            }
        }

    }


}


class Person1 {
    private String name;
    private String address;
    private Integer age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}


class Person2{

    private String name;
    private String address;
    private String email;



    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
