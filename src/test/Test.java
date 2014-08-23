package test;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.CommandFactory;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by gaochuanjun on 14-8-23.
 */
public class Test {

    public static void main(String[] args) {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(); //将相关的规则文件进行编译，产生对应的KnowledgePackage集合
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("test.drl", Test.class), ResourceType.DRL);
        if (knowledgeBuilder.hasErrors()) {
            System.out.println("规则中存在错误，错误消息如下：");
            KnowledgeBuilderErrors knowledgeBuilderErrors = knowledgeBuilder.getErrors();
            for (Iterator iterator = knowledgeBuilderErrors.iterator(); iterator.hasNext(); ) {
                System.out.println(iterator.hasNext());
            }
        }
        Collection<KnowledgePackage> kpackage = knowledgeBuilder.getKnowledgePackages(); //产生规则包的集合

        Properties properties = new Properties();
        properties.setProperty("org.drools.sequential", "true");
        //KnowledgeBaseConfiguration kbconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        KnowledgeBaseConfiguration kbconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration(properties, null);

        //kbconf.setProperty("org.drools.sequential", "true");

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbconf); //通过 KnowledgeBase把产生的KnowledgePackage集合收集起来
        kbase.addKnowledgePackages(kpackage);//将KnowledgePackage集合添加到KnowledgeBase当中

        //StatefulKnowledgeSession statefulKnowledgeSession = kbase.newStatefulKnowledgeSession();
        //statefulKnowledgeSession.setGlobal("globalTest", new Object());//设置一个global对象
        //statefulKnowledgeSession.insert(new Object()); //插入一个fact对象
        //statefulKnowledgeSession.fireAllRules();//触发所有的规则执行
        //statefulKnowledgeSession.dispose(); //释放内存资源
        StatelessKnowledgeSession statelessKnowledgeSession = kbase.newStatelessKnowledgeSession();
        ArrayList list = new ArrayList();
        //list.add(new Object());
        //list.add(new Object());
        list.add(CommandFactory.newInsert(new Object()));
        list.add(CommandFactory.newInsert(new Object()));
        list.add(CommandFactory.newSetGlobal("key1", new Object()));
        list.add(CommandFactory.newSetGlobal("key2", new Object()));

        //statelessKnowledgeSession.execute(list);
        //statelessKnowledgeSession.execute(CommandFactory.newInsert(list));
        statelessKnowledgeSession.execute(CommandFactory.newBatchExecution(list));
    }

}
