package test;

import net.drools.bean.Order;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Created by gaochuanjun on 14-8-24.
 */
public class FactDemo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();

        kb.add(new ClassPathResource("test/factdemo.drl"), ResourceType.DRL);
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(kb.getKnowledgePackages());

        Order order = new Order();
        order.setName("测试订单");

        FactType addressType = knowledgeBase.getFactType("test", "Address");
        Object add = addressType.newInstance();
        addressType.set(add, "city", "中国上海");
        addressType.set(add, "addressName", "中国上海浦东新区");

        FactType persopnFact = knowledgeBase.getFactType("test", "Person");
        Object obj = persopnFact.newInstance();
        persopnFact.set(obj, "name", "steven.gao");
        persopnFact.set(obj, "order", order);
        persopnFact.set(obj, "address", add);
        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulSession.insert(obj);
        statefulSession.fireAllRules();
        statefulSession.dispose();
    }

}
