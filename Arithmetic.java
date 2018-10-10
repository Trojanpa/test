/**
 *  实现功能：随机生成运算长度为2~3个运算符的小学四则混合运算并写入到 titleAndResult.txt 文本中
 *  编译者：胡雄柏
 *  编写时间：2018/10/2
 * */
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Arithmetic {
    private static int titleNum = 0;                                //用户输入四则混合运算题数
    private static int arithmLength = 0;                            //随机生成算术式运算长度
    private static String[] operators = {"+", "-", "*", "/"};      //运算符号
    private static int[] properFractionResult=new int[2];          //真分数题目结果，下标为0的是分子，下标为1得是分母

    public static void main(String[] args) {
        System.out.println("\n\t\t\t\t\t这是一个关于四则运算法则的小程序\t\t\t\t\t\n");
        System.out.println("系统会根据用户输入的题目数量自动随机生成题目\n");
        System.out.print("请输入四则混合运算题的数量(要求：输入正整数)：");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();                               //input存储用户键盘输入的数据
        try {
            titleNum = Integer.valueOf(input);                      //判断是不是整形数字
        } catch (Exception e) {
            System.out.println("\n请按要求输入!\n");
            main(args);
            return;
        };
        randomTitle();
        System.out.println("\n下面有10道真分数题目，请作答（约分要求约到最简，例如：8/19 例如： -987/1000）。\n");
        randomProperFraction();
        System.out.println("\n\t\t\t再见！\n");
        System.exit(0);
    }

    /**
     * 在静态方法中不能访问非静态成员方法和非静态成员变量，但是在非静态成员方法中是可以访问静态成员方法/变量
     * static变量也称作静态变量，静态变量和非静态变量的区别是：静态变量被所有的对象所共享，在内存中只有一个副本，它当且
     * 仅当在类初次加载时会被初始化。而非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响。
     **/

    //随机生成四则混合运算式题目及结果
    private static void randomTitle() {
        int writer = 0;                                                    //习题的开头判断标志
        try {
            File file = new File("titleAndResult.txt");      //创建或打开文件
            FileWriter fileWriter = new FileWriter(file, true);  //往文件写入字符串
            PrintWriter printWriter = new PrintWriter(fileWriter);         //打印写入的内容
            if (writer == 0) {
                printWriter.println("四则运算题及答案：");
                writer = 1;
            }
            System.out.println("四则运算题及答案：");

            //多少题循环多少次
            for (int i = 1; i <= titleNum; i++) {
                int data1 = (int) (Math.random() * 100 + 1);                   //随机生成第一个运算数字
                int data2 = (int) (Math.random() * 100 + 1);                   //随机生成第二个运算数字
                int data3 = (int) (Math.random() * 100 + 1);                   //随机生成第三个运算数字
                int data4 = (int) (Math.random() * 100 + 1);                   //随机生成第四个运算数字
                //int data5=(int)(Math.random()*100+1);                        //随机生成第五个运算数字

                int op1 = (int) (Math.random() * 4);                          //随机生成第一个运算符下标
                int op2 = (int) (Math.random() * 4);                          //随机生成第二个运算符下标
                int op3 = (int) (Math.random() * 4);                          //随机生成第三个运算符下标
                //int op4=(int)(Math.random()*4);                             //随机生成第四个运算符下标

                String str1 = data1 + operators[op1] + data2 + operators[op2] + data3;                              //存储运算符长度为2的运算题目
                String str2 = data1 + operators[op1] + data2 + operators[op2] + data3 + operators[op3] + data4;     //存储运算符长度为3的运算题目
                //String str3=data1+operators[op1]+data2+operators[op2]+data3+operators[op3]+data4+operators[op4]+data5;

                long result;                                                //存算术式的运算结果

                arithmLength = (int) (Math.random() * 2 + 3);              //算术式长度(3到4个数字)

                //根据运算数长度随机生成题目并计算该题目的结果
                switch (arithmLength) {
                    case 3:
                        if (operators[op1] == "+") {
                            if (operators[op2] == "+") {
                                result = data1 + data2 + data3;
                                System.out.println(str1 + "=" + result);
                                printWriter.println(str1 + "=" + result);
                            } else if (operators[op2] == "-") {
                                result = data1 + data2 - data3;
                                if (result <= 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "*") {
                                result = data1 + data2 * data3;
                                System.out.println(str1 + "=" + result);
                                printWriter.println(str1 + "=" + result);
                            } else if (operators[op2] == "/") {
                                result = data1 + data2 / data3;
                                if (result <= 0 || data2 % data3 != 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            }
                        } else if (operators[op1] == "-") {
                            if (operators[op2] == "+") {
                                result = data1 - data2 + data3;
                                if (result < 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "-") {
                                result = data1 - data2 - data3;
                                if (result < 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "*") {
                                result = data1 - data2 * data3;
                                if (result <= 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "/") {
                                result = data1 - data2 / data3;
                                if (result <= 0 || data2 % data3 != 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            }
                        } else if (operators[op1] == "*") {
                            if (operators[op2] == "+") {
                                result = data1 * data2 + data3;
                                System.out.println(str1 + "=" + result);
                                printWriter.println(str1 + "=" + result);
                            } else if (operators[op2] == "-") {
                                result = data1 * data2 - data3;
                                if (result <= 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "*") {
                                result = data1 * data2 * data3;
                                System.out.println(str1 + "=" + result);
                                printWriter.println(str1 + "=" + result);
                            } else if (operators[op2] == "/") {
                                result = data1 * data2 / data3;
                                if (result <= 0 || data2 % data3 != 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            }
                        } else if (operators[op1] == "/") {
                            if (operators[op2] == "+") {
                                result = data1 / data2 + data3;
                                if (result <= 0 || data1 % data2 != 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "-") {
                                result = data1 / data2 - data3;
                                if (result <= 0 || data1 % data2 != 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "*") {
                                result = data1 / data2 * data3;
                                if (result <= 0 || data1 % data2 != 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            } else if (operators[op2] == "/") {
                                result = data1 / data2 / data3;
                                if (result <= 0 || data1 % data2 != 0 || data1 / data2 % data3 != 0) {
                                    i--;
                                } else {
                                    System.out.println(str1 + "=" + result);
                                    printWriter.println(str1 + "=" + result);
                                }
                            }
                        }
                        break;
                    case 4:
                        if (operators[op1] == "+") {
                            if (operators[op2] == "+") {
                                if (operators[op3] == "+") {
                                    result = data1 + data2 + data3 + data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                } else if (operators[op3] == "-") {
                                    result = data1 + data2 + data3 - data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 + data2 + data3 * data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                } else if (operators[op3] == "/") {
                                    result = data1 + data2 + data3 / data4;
                                    if (result <= 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "-") {
                                if (operators[op3] == "+") {
                                    result = data1 + data2 - data3 + data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 + data2 - data3 - data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 + data2 - data3 * data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 + data2 - data3 / data4;
                                    if (result <= 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "*") {
                                if (operators[op3] == "+") {
                                    result = data1 + data2 * data3 + data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                } else if (operators[op3] == "-") {
                                    result = data1 + data2 * data3 - data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 + data2 * data3 * data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                } else if (operators[op3] == "/") {
                                    result = data1 + data2 * data3 / data4;
                                    if (result <= 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "/") {
                                if (operators[op3] == "+") {
                                    result = data1 + data2 / data3 + data4;
                                    if (result <= 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 + data2 / data3 - data4;
                                    if (result <= 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 + data2 / data3 * data4;
                                    if (result <= 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 + data2 / data3 / data4;
                                    if (result <= 0 || data2 % data3 != 0 || data2 / data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            }
                        } else if (operators[op1] == "-") {
                            if (operators[op2] == "+") {
                                if (operators[op3] == "+") {
                                    result = data1 - data2 + data3 + data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 - data2 + data3 - data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 - data2 + data3 * data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 - data2 + data3 / data4;
                                    if (result <= 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "-") {
                                if (operators[op3] == "+") {
                                    result = data1 - data2 - data3 + data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 - data2 - data3 - data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }

                                } else if (operators[op3] == "*") {
                                    result = data1 - data2 - data3 * data4;
                                    if (result <= 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 - data2 - data3 / data4;
                                    if (result <= 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "*") {
                                if (operators[op3] == "+") {
                                    result = data1 - data2 * data3 + data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 - data2 * data3 - data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 - data2 * data3 * data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 - data2 * data3 / data4;
                                    if (result < 0 || data3 % data4 != 0 || data2 * data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "/") {
                                if (operators[op3] == "+") {
                                    result = data1 - data2 / data3 + data4;
                                    if (result < 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 - data2 / data3 - data4;
                                    if (result < 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 - data2 / data3 * data4;
                                    if (result < 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 - data2 / data3 / data4;
                                    if (result < 0 || data2 % data3 != 0 || data2 / data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            }
                        } else if (operators[op1] == "*") {
                            if (operators[op2] == "+") {
                                if (operators[op3] == "+") {
                                    result = data1 * data2 + data3 + data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                } else if (operators[op3] == "-") {
                                    result = data1 * data2 + data3 - data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                                if (operators[op3] == "*") {
                                    result = data1 * data2 + data3 * data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                }
                                if (operators[op3] == "/") {
                                    result = data1 * data2 + data3 / data4;
                                    if (result < 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "-") {
                                if (operators[op3] == "+") {
                                    result = data1 * data2 - data3 + data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 * data2 - data3 - data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                                if (operators[op3] == "*") {
                                    result = data1 * data2 - data3 * data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                                if (operators[op3] == "/") {
                                    result = data1 * data2 - data3 / data4;
                                    if (result < 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "*") {
                                if (operators[op3] == "+") {
                                    result = data1 * data2 * data3 + data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                } else if (operators[op3] == "-") {
                                    result = data1 * data2 * data3 - data4;
                                    if (result < 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                                if (operators[op3] == "*") {
                                    result = data1 * data2 * data3 * data4;
                                    System.out.println(str2 + "=" + result);
                                    printWriter.println(str2 + "=" + result);
                                }
                                if (operators[op3] == "/") {
                                    result = data1 * data2 * data3 / data4;
                                    if (result < 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "/") {
                                if (operators[op3] == "+") {
                                    result = data1 * data2 / data3 + data4;
                                    if (result < 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 * data2 / data3 - data4;
                                    if (result < 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                                if (operators[op3] == "*") {
                                    result = data1 * data2 / data3 * data4;
                                    if (result < 0 || data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                                if (operators[op3] == "/") {
                                    result = data1 * data2 / data3 / data4;
                                    if (result < 0 || data2 % data3 != 0 || data2 / data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            }
                        } else if (operators[op1] == "/") {
                            if (operators[op2] == "+") {
                                if (operators[op3] == "+") {
                                    result = data1 / data2 + data3 + data4;
                                    if (result < 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 / data2 + data3 - data4;
                                    if (result < 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 / data2 + data3 * data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 / data2 + data3 / data4;
                                    if (result <= 0 || data1 % data2 != 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "-") {
                                if (operators[op3] == "+") {
                                    result = data1 / data2 - data3 + data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 / data2 - data3 - data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 / data2 - data3 * data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 / data2 - data3 / data4;
                                    if (result <= 0 || data1 % data2 != 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "*") {
                                if (operators[op3] == "+") {
                                    result = data1 / data2 * data3 + data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 / data2 * data3 - data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 / data2 * data3 * data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 / data2 * data3 / data4;
                                    if (result <= 0 || data1 % data2 != 0 || data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            } else if (operators[op2] == "/") {
                                if (operators[op3] == "+") {
                                    result = data1 / data2 / data3 + data4;
                                    if (result <= 0 || data1 % data2 != 0 || data1 / data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "-") {
                                    result = data1 / data2 / data3 - data4;
                                    if (result <= 0 || data1 % data2 != 0 || data1 / data2 % data3 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "*") {
                                    result = data1 / data2 / data3 * data4;
                                    if (result <= 0 || data1 % data2 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                } else if (operators[op3] == "/") {
                                    result = data1 / data2 / data3 / data4;
                                    if (result <= 0 || data1 % data2 != 0 || data1 / data2 % data3 != 0 || data1 / data2 / data3 % data4 != 0) {
                                        i--;
                                    } else {
                                        System.out.println(str2 + "=" + result);
                                        printWriter.println(str2 + "=" + result);
                                    }
                                }
                            }
                        }
                        break;
                }
            }
            printWriter.flush();
            printWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            return;
        };
    }

    //随机生成真分数题目
    private static void randomProperFraction(){
        float correctRate=0;                                               //答题正确率
        int correctAnswerNum=0;                                            //真分数答对题目数
        int i,j,k;                                                         //循环变量
        int rand1=0,rand2=0;                                               //随机生成运算符下标
        int properFractionTitleNum=10;                                     //10道真分数题
        int properFractionTitleLength;                                     //题目长度
        String[] properFractionTitle=new String[10];                       //真分数题目

        int writer = 0;                                                    //习题的开头判断标志
        try {
            File file = new File("titleAndResult.txt");      //创建或打开文件
            FileWriter fileWriter = new FileWriter(file, true);  //往文件写入字符串
            PrintWriter printWriter = new PrintWriter(fileWriter);         //打印写入的内容
            if (writer == 0) {
                printWriter.println("真分数运算题及答案：");
                writer = 1;
            }
            System.out.println("真分数运算题及答案：");

            for (i = 0; i < properFractionTitleNum; i++) {
                for (j = 0; j < 2; j++) {
                    properFractionResult[j] = 0;
                }
                properFractionTitleLength = (int) (Math.random() * 2 + 2);
                int[] numerator = new int[properFractionTitleLength];     //分子
                int[] denominator = new int[properFractionTitleLength];   //分母
                switch (properFractionTitleLength) {
                    case 2:
                        for (j = 0; j < 2; j++) {
                            denominator[j] = (int) (Math.random() * 100 + 1);
                            numerator[j] = (int) (Math.random() * denominator[j] + 1);
                        }
                        rand1 = (int) (Math.random() * 4);
                        calculateProperFraction(numerator[0], denominator[0], rand1, numerator[1], denominator[1]);
                        reductionFraction(properFractionResult[0], properFractionResult[1]);
                        properFractionTitle[i] = numerator[0] + "/" + denominator[0] + "  " + operators[rand1] + "  " + numerator[1] + "/" + denominator[1] + " = ";
                        break;
                    case 3:
                        for (k = 0; k < 3; k++) {
                            denominator[k] = (int) (Math.random() * 100 + 1);
                            numerator[k] = (int) (Math.random() * denominator[k] + 1);
                        }
                        rand1 = (int) (Math.random() * 4);
                        rand2 = (int) (Math.random() * 4);
                        if (rand1 == 2 || rand1 == 3) {                      //如果第一个运算符是乘或者除就先算
                            calculateProperFraction(numerator[0], denominator[0], rand1, numerator[1], denominator[1]);
                            calculateProperFraction(properFractionResult[0], properFractionResult[1], rand2, numerator[2], denominator[2]);
                        } else {
                            calculateProperFraction(numerator[1], denominator[1], rand2, numerator[2], denominator[2]);
                            calculateProperFraction(numerator[0], denominator[0], rand1, properFractionResult[0], properFractionResult[1]);
                        }
                        reductionFraction(properFractionResult[0], properFractionResult[1]);
                        properFractionTitle[i] = numerator[0] + "/" + denominator[0] + "  " + operators[rand1] + "  " + numerator[1] + "/" + denominator[1] + "  " + operators[rand2] + "  " + numerator[2] + "/" + denominator[2] + " = ";
                        break;
                }
                if (properFractionResult[0] < properFractionResult[1] && properFractionResult[0]>0) {
                    System.out.print(properFractionTitle[i]);
                    printWriter.println(properFractionTitle[i]+properFractionResult[0] + "/" + properFractionResult[1]);
                    Scanner scanner = new Scanner(System.in);
                    String string = scanner.next();
                    if (string.equals(properFractionResult[0] + "/" + properFractionResult[1])) {
                        correctAnswerNum += 1;
                        System.out.println("恭喜你，答对了！\n");
                    } else {
                        System.out.println("哎呀，答错了！细心点答题更容易答对哦，加油吧！");
                        System.out.println("正确答案为：" + properFractionResult[0] + "/" + properFractionResult[1] + "\n");
                    }
                } else {
                    i--;
                }
            }
            correctRate = (float) correctAnswerNum / 10 * 100;
            System.out.println("您已答完所有的题目。");
            System.out.println("您共答对了" + correctAnswerNum + "道题," + "正确率为：" + correctRate + "%");
            System.out.println("得分为：" + correctAnswerNum*10+"分");

            printWriter.flush();
            printWriter.close();
            fileWriter.close();
        }catch (Exception e) {
            return;
        };
    }

    //计算两个分数的运算结果
    private static void calculateProperFraction(int numerator1,int denominator1,int rand,int numerator2,int denominator2){
        switch (rand){
            case 0://如果是加号
                properFractionResult[1]=denominator1*denominator2;
                properFractionResult[0]=numerator1*denominator2+numerator2*denominator1;
                break;
            case 1://如果是减号
                properFractionResult[1]=denominator1*denominator2;
                properFractionResult[0]=numerator1*denominator2 - numerator2*denominator1;
                break;
            case 2://如果是乘号
                properFractionResult[1]=denominator1*denominator2;
                properFractionResult[0]=numerator1*numerator2;
                break;
            case 3://如果是除号
                properFractionResult[1]=denominator1*numerator2;
                properFractionResult[0]=numerator1*denominator2;
                break;
        }
    }

    //将分数约分约到最简
    private static void reductionFraction(int numerator,int denominator){
        int i=0;
        int reductionFractionNumerator=0,reductionFractionDenominator=0;    //约分后的的分子、分母
        int commonDivisor=1;
        for (i=numerator;i>=1;i--){
            if (numerator%i==0 && denominator%i==0){
                commonDivisor=i;
                break;
            }
        }
        reductionFractionNumerator=numerator/commonDivisor;
        reductionFractionDenominator=denominator/commonDivisor;
        properFractionResult[0]=reductionFractionNumerator;                //约分后的的分子赋给properFractionResult[0]
        properFractionResult[1]=reductionFractionDenominator;              //约分后的的分母赋给properFractionResult[1]
    }
}
