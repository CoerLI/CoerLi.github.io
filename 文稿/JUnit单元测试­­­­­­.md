## JUnit单元测试­­­­­­

### 注解：必须是public void方法

@Test：，表明要测试的方法

@BeforeClass：必须是public static void，表明在所有测试执行之前就执行，例如连接数据库

@Before：每个test执行之前都要执行

@AfterClass：必须是static 方法，表明在所有测试执行之后执行，例如断开数据库连接

@After：每个test执行之后都要执行

@Ignore：忽略此方法

 

### 断言Assert

assertEquals(failureMsg, excepted, actual)      检测对象是否equals

assertArrayEquals(msg,excepted,actual)    检测数组是否equals

assertFalse(msg,false/true)

assertNull(msg,obj)

assertNotNull(msg,obj)

assertSame(msg,excepted,actual)         通过==比较，只能比较对象

assertNotSame(msg,excepted,actual)

 

assertThat()    复杂的断言

allOf()  满足全部条件

anyOr() 满足任意一个条件

is() 两个对象equals 则通过

not() 两个对象not equals则通过