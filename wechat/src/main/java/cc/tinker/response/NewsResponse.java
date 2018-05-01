package cc.tinker.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsResponse extends BaseResponse {

	private Integer ArticleCount;
	private List<Article> Articles = new ArrayList<>();

	public NewsResponse() {
		this.setMsgType(Response.NEWS);
		this.ArticleCount = 0;
	}

	public NewsResponse(Map<String, String> map) {
		super(map);
		this.setMsgType(Response.NEWS);
		this.ArticleCount = 0;
	}

	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
		ArticleCount = Articles.size();
	}

	public void addItem(Article article) {
		Articles.add(article);
		ArticleCount++;
	}

}
