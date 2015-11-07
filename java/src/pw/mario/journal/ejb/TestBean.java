package pw.mario.journal.ejb;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.primefaces.push.annotation.Singleton;

import lombok.Data;
import lombok.NoArgsConstructor;

public class TestBean {
	public TestBean() {
		testList = new LinkedList<>();
		testList.add("Mario");
		testList.add("Bro");
	}
	
	private List<String> testList;

	public List<String> getTestList() {
		return testList;
	}

	public void setTestList(List<String> testList) {
		this.testList = testList;
	}
}
