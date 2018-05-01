package cc.tinker.util;

import cc.tinker.response.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

	public static Map<String, String> parseXml(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try (InputStream is = request.getInputStream()) {
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			convert(root, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private static void convert(Element root, Map<String, String> map) {
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		for (Element e : elements) {
			if (e.elements().size() > 0) {
				convert(e, map);
			} else {
				map.put(e.getName(), e.getText());
			}
		}
	}

	public static String parseString(HttpServletRequest request) {
		String result = null;
		try (InputStream in = request.getInputStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = in.read(bytes)) != -1) {
				baos.write(bytes, 0, length);
			}
			result = baos.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				public void startNode(String name, @SuppressWarnings("rawtypes") Class classs) {
					super.startNode(name, classs);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static String toXml(TextResponse text) {
		xstream.alias("xml", text.getClass());
		return xstream.toXML(text);
	}

	public static String toXml(ImageResponse image) {
		xstream.alias("xml", image.getClass());
		return xstream.toXML(image);
	}

	public static String toXml(VoiceResponse voice) {
		xstream.alias("xml", voice.getClass());
		return xstream.toXML(voice);
	}

	public static String toXml(VideoResponse video) {
		xstream.alias("xml", video.getClass());
		return xstream.toXML(video);
	}

	public static String toXml(MusicResponse music) {
		xstream.alias("xml", music.getClass());
		return xstream.toXML(music);
	}

	public static String toXml(NewsResponse news) {
		xstream.alias("xml", news.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(news);
	}

}
