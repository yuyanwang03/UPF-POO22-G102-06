
import java.io.File;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utility {
	public static LinkedList< String[] > readXML( String type ) {
		LinkedList< String > tokens = new LinkedList< String >();
		if ( type == "headquarter" ) {
			tokens.add( "name" );
                        tokens.add( "email" );
                        tokens.add( "head" );
                        tokens.add( "city" );
                        
		}
		if ( type == "head" ) {
			tokens.add( "name" );
			tokens.add( "phone" );
                        tokens.add( "email" );
                        tokens.add("headOf");
                        tokens.add( "days" );
                        tokens.add( "hours" );
		}
		if ( type == "region" ) {
			tokens.add( "name" );
                        tokens.add( "city_name" );
                        tokens.add( "city_population" );
		}
		return readXML( type, tokens );
	}	

	public static LinkedList< String[] > readXML( String type, LinkedList< String > tokens ) {
		LinkedList< String[] > outputs = new LinkedList< String[] >();
		try {
			File input = new File("src/" + type + "s.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse( input );
			doc.getDocumentElement().normalize();
			NodeList list = doc.getElementsByTagName( type );

            for ( int i = 0; i < list.getLength(); ++i ) {
                Element element = (Element)list.item( i );
				LinkedList<String> output = new LinkedList<String>();
				for ( String token : tokens ) {
					NodeList nl = element.getElementsByTagName( token );
                    for ( int k = 0; k < nl.getLength(); ++k ) {
                        Element e = (Element)nl.item( k );
						output.add( e.getChildNodes().item(0).getNodeValue() );
					}
				}
				outputs.add( output.toArray( new String[0] ) );
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit( 0 );
		}
		return outputs;
	}

    public static <T> T getObject( String desc, LinkedList< T > objectList ) {
        for ( T object : objectList )
            if ( desc.equals( object.toString() ) )
                return object;
        return null;
    }

    public static <T> LinkedList< String > toString( LinkedList< T > objectList ) {
		LinkedList< String > output = new LinkedList< String >();
        for ( T object : objectList )
			output.add( object.toString() );
        return output;
    }
}
