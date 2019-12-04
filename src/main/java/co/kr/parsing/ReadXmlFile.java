package co.kr.parsing;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import co.kr.domain.ClassType;
import co.kr.domain.Customer;
import co.kr.domain.Order;
import co.kr.domain.Product;
import co.kr.domain.TotalManager;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXmlFile {
	private ClassType type = ClassType.NONE;
	private TotalManager totalManager;
	
	public ClassType getType() {
		return type;
	}

	public void setType(ClassType type) {
		this.type = type;
	}

	public TotalManager getTotalManager() {
		return totalManager;
	}

	public void setTotalManager(TotalManager totalManager) {
		this.totalManager = totalManager;
	}

	public void readXml(String fileName) {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName));
			document.getDocumentElement().normalize();
			NodeList nodeList = null;
			
			//파일을 읽었을때 사용된 목적이 무엇인지 알기위해 루트 태그를 읽어와 타입을 결정한다.
			if(document.getDocumentElement().getNodeName().contains("customer")) {
				setType(ClassType.CUSTOMER);
				nodeList = document.getElementsByTagName("customerInfo");
			}
			else if(document.getDocumentElement().getNodeName().contains("order")) {
				setType(ClassType.ORDER);
				nodeList = document.getElementsByTagName("orderInfo");
			}
			else if(document.getDocumentElement().getNodeName().contains("product")) {
				setType(ClassType.PRODUCT);
				nodeList = document.getElementsByTagName("productInfo");
			}
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elemnet = (Element) node;
					
					if(getType() == ClassType.CUSTOMER) {
						Customer obj = new Customer();
						obj.setCustomId(elemnet.getElementsByTagName("customerId").item(0).getTextContent());
						obj.setCustomName(elemnet.getElementsByTagName("customerName").item(0).getTextContent());
						//핸들러 클래스에 저장된 Map에 저장시킨다.
						//다른 클래스를 참조하는 부분임 바꿔야됨.
						getTotalManager().getCustomMap().put(obj.getCustomId(), obj);
					}
					else if(getType() == ClassType.ORDER) {
						Order obj = new Order();
						obj.setOrderId(elemnet.getElementsByTagName("orderId").item(0).getTextContent());
						obj.setCustomId(elemnet.getElementsByTagName("customerId").item(0).getTextContent());
						obj.setProductId(elemnet.getElementsByTagName("productId").item(0).getTextContent());
						//핸들러 클래스에 저장된 리스트에 저장시킨다.
						//다른 클래스를 참조하는 부분임 바꿔야됨.
						getTotalManager().getOrderList().add(obj);
					}
					else if(getType() == ClassType.PRODUCT) {
						Product obj = new Product();
						obj.setProductId(elemnet.getElementsByTagName("productId").item(0).getTextContent());
						obj.setProductName(elemnet.getElementsByTagName("productName").item(0).getTextContent());
						//핸들러 클래스에 저장된 Map에 저장시킨다.
						//다른 클래스를 참조하는 부분임 바꿔야됨.
						getTotalManager().getProductMap().put(obj.getProductId(), obj);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}




	}

}

