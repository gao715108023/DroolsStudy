package test;

import net.drools.bean.Customer;
import net.drools.bean.Order;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaochuanjun on 14-8-24.
 */
public class TestQuery {

    public static void main(String[] args) {
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();

        kb.add(new ClassPathResource("test/query.drl"), ResourceType.DRL);
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(kb.getKnowledgePackages());
        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();

        //statefulSession.insert(generateCustomer("张三", 20, 21));
        //statefulSession.insert(generateCustomer("李四", 33, 11));
        //statefulSession.insert(generateCustomer("王二", 43, 12));
        statefulSession.insert(generateCustomer("张三", 20, "F"));
        statefulSession.insert(generateCustomer("李四", 33, "M"));
        statefulSession.insert(generateCustomer("王二", 43, "F"));

        QueryResults queryResults = statefulSession.getQueryResults("testQuery1", new Object[]{new Integer(20), "F"});
        for (QueryResultsRow qr : queryResults) {
            Customer cus = (Customer) qr.get("customer");

            System.out.println("customer name: " + cus.getName());
        }

        statefulSession.dispose();
    }

    public static Customer generateCustomer(String name, int age, int orderSize) {
        Customer cus = new Customer();
        cus.setName(name);
        cus.setAge(age);
        List ls = new ArrayList();
        for (int i = 0; i < orderSize; i++) {
            ls.add(new Order());
        }
        cus.setOrders(ls);
        return cus;
    }

    public static Customer generateCustomer(String name, int age, String gender) {
        Customer cus = new Customer();
        cus.setName(name);
        cus.setAge(age);
        cus.setGender(gender);
        return cus;
    }
}
