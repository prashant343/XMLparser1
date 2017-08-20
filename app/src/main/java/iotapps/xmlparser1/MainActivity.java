package iotapps.xmlparser1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    TextView hellotext;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    hellotext = (TextView) findViewById(R.id.textview);
    context = getBaseContext();



        try {
           // InputStream is = getAssets().open("xmlfile.xml");
            InputStream is = getResources().openRawResource(R.raw.xmlfile);


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("table");
            Log.d("no.s",String.valueOf(nList.getLength()));


            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    hellotext.setText(hellotext.getText()+"\nName : " + getValue("name", element2)+"\n");
                    hellotext.setText(hellotext.getText()+"cost : " + getValue("cost", element2)+"\n");
                    hellotext.setText(hellotext.getText()+"-----------------------");
                }

            }

        } catch (Exception e) {e.printStackTrace();}

    }




    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
