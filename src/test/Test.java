package test;

import net.drools.bean.Customer;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by gaochuanjun on 14-8-23.
 */
public class Test {

    public String getPath() {
        return this.getClass().getResource("").getPath();
    }

    public static void main(String[] args) {
        //Test test = new Test();
        //String classPath = test.getPath();
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder(); //将相关的规则文件进行编译，产生对应的KnowledgePackage集合
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
        kb.add(ResourceFactory.newClassPathResource("test/test.drl", Test.class), ResourceType.DRL);
        if (kb.hasErrors()) {
            System.out.println("规则中存在错误，错误消息如下：");
            KnowledgeBuilderErrors knowledgeBuilderErrors = kb.getErrors();
            for (Iterator iterator = knowledgeBuilderErrors.iterator(); iterator.hasNext(); ) {
                System.out.println(iterator.hasNext());
            }
        }
        Collection<KnowledgePackage> kpackage = kb.getKnowledgePackages(); //产生规则包的集合

        //Properties properties = new Properties();
        //properties.setProperty("org.drools.sequential", "true");
        //KnowledgeBaseConfiguration kbconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        //KnowledgeBaseConfiguration kbconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration(properties, null);

        //kbconf.setProperty("org.drools.sequential", "true");

        //KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbconf); //通过 KnowledgeBase把产生的KnowledgePackage集合收集起来
        //kbase.addKnowledgePackages(kpackage);//将KnowledgePackage集合添加到KnowledgeBase当中
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(kpackage);

        //StatefulKnowledgeSession statefulKnowledgeSession = kbase.newStatefulKnowledgeSession();
        //statefulKnowledgeSession.setGlobal("globalTest", new Object());//设置一个global对象
        //statefulKnowledgeSession.insert(new Object()); //插入一个fact对象
        //statefulKnowledgeSession.fireAllRules();//触发所有的规则执行
        //statefulKnowledgeSession.dispose(); //释放内存资源
        //StatelessKnowledgeSession statelessKnowledgeSession = kbase.newStatelessKnowledgeSession();
        //ArrayList list = new ArrayList();
        //list.add(new Object());
        //list.add(new Object());
        //list.add(CommandFactory.newInsert(new Object()));
        //list.add(CommandFactory.newInsert(new Object()));
        //list.add(CommandFactory.newSetGlobal("key1", new Object()));
        //list.add(CommandFactory.newSetGlobal("key2", new Object()));

        //statelessKnowledgeSession.execute(list);
        //statelessKnowledgeSession.execute(CommandFactory.newInsert(list));
        //statelessKnowledgeSession.execute(CommandFactory.newBatchExecution(list));
        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        //Customer cusl = new Customer();
        //cusl.setName("张三");
        //Customer cus2 = new Customer();
        //cus2.setName("李四");
        //Customer cus3 = new Customer();
        //cus3.setName("王二");
        //Customer cus4 = new Customer();
        //cus4.setName("李小龙");

        //statefulKnowledgeSession.insert(cusl);
        //statefulKnowledgeSession.insert(cus2);
        //statefulKnowledgeSession.insert(cus3);
        //statefulKnowledgeSession.insert(cus4);

        //Customer cus = new Customer();
        //
        //cus.setAge(20);
        //cus.setId("Zhangsan");
        //cus.setName("张三");
        //
        //statefulKnowledgeSession.insert(cus);
        //statefulKnowledgeSession.getAgenda().getAgendaGroup("002").setFocus();
        //AgendaFilter filter = new TestAgendaFilter("rule1");

        //statefulKnowledgeSession.fireAllRules(filter);

        statefulKnowledgeSession.insert(new Customer());
        statefulKnowledgeSession.fireAllRules();


        //QueryResults qr = statefulKnowledgeSession.getQueryResults("query fact count");
        //System.out.println("customer对象数目： " + qr.size());
        System.out.println("end......");
        //System.out.println("current thread id: " + Thread.currentThread().getId());
        //try {
        //    Thread.sleep(4000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        statefulKnowledgeSession.dispose();


    }

}
