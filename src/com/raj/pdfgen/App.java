package com.raj.pdfgen;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class App 
{
    public static void main( String[] args ) throws DocumentException, IOException
    {
      try {
 			Document document = new Document();
 			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
 		    document.open();
 		    //XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("index.html"));	
		    
		    URL u = new URL("http://validator.w3.org/");
		    URL css = new URL("http://validator.w3.org/style/base");
		    
            org.jsoup.nodes.Document doc = Jsoup.parse(u,10000); 
            String x = doc.toString();
            File file = new File("newfile.html");
    		FileOutputStream fop = new FileOutputStream(file); 	
    		
    		if (!file.exists()) {
				file.createNewFile();
			}
    		
    		fop.write(x.getBytes());
			fop.flush();
			fop.close();
            
            InputStream in = new ByteArrayInputStream(x.getBytes());
            InputStream styleStream = css.openStream();
            
            
		  //  XMLWorkerHelper.getInstance().parseXHtml(writer, document,in);
		 //XMLWorkerHelper.getInstance().parseXHtml(writer, document,in,styleStream);
		   XMLWorkerHelper.getInstance().parseXHtml(writer, document, in, new FileInputStream("base.css"));
		    //step 5
		     document.close();

		    System.out.println( "PDF Created!" );
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
