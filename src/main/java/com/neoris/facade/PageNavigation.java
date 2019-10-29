package com.neoris.facade;

public interface PageNavigation {

	public int getCurrentPage();

	public int getCurrentRow();

	public int getPageCount();

	public int getRowsByPage();

	public boolean isTheFirstPage();

	public boolean isTheLastPage();

	public String page();

	public void setCurrentPage(int currentPage);

	public void setRowsByPage(int rowsByPage);

}
