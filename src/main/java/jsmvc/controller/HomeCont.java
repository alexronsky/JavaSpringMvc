package jsmvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jsmvc.dao.TableItemDao;
import jsmvc.model.TableItem;

@Controller
public class HomeCont {
	@Autowired TableItemDao tableItemDao;

	@RequestMapping(value="/", method=RequestMethod.GET)
	protected String homeGet(Model model, HttpServletRequest request) {
		fillTableItems(model, request);
		return "home";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	protected String homePost(Model model, HttpServletRequest request) {
    	deleteItems(request);
    	addNewItem(model, request);
    	fillTableItems(model, request);
		return "home";
	}
	
	private void fillTableItems(Model model, HttpServletRequest request) {
		List<TableItem> items = tableItemDao.getTableItems();
		model.addAttribute("tableItems", items);
	}
    
    private void deleteItems(HttpServletRequest request) {
		String[] results = request.getParameterValues("deleteItems");
		
		if (results != null) {
			for (int i = 0; i < results.length; i++) {
				try {
					int id = Integer.valueOf(results[i]);
					tableItemDao.deleteItem(id);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    private void addNewItem(Model model, HttpServletRequest request) {
    	String newName = request.getParameter("newName");
    	String newDateStr = request.getParameter("newDate");
    	Date newDate = null;
    	
    	if (newDateStr != null) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				newDate = formatter.parse(newDateStr);
			} catch (ParseException e) {
				model.addAttribute("error", "Неверный формат даты");
				model.addAttribute("newName", newName);
				model.addAttribute("newDate", newDateStr);
			}
    	}    	
    	if (newName != null && newDate != null) {
    		tableItemDao.addItem(new TableItem(newName, newDate));
    	}
    }
}



