package org.spo.svc2.trx.pgs.mh01.cmd;


public class M_Home_01
{
	private String section_list_heading;

	private String section_list_present_ic;

	private SECTION_LINKS_ZN[] SECTION_LINKS_ZN;

	private String page_sub_title;

	private String section_list_qtt;

	private String page_content_present_ic;

	private String page_content_text;

	private String page_content_type_cd;

	private String page_description_text;

	private String list_pagination_ic;

	private String page_title;

	public String getSection_list_heading ()
	{
		return section_list_heading;
	}

	public void setSection_list_heading (String section_list_heading)
	{
		this.section_list_heading = section_list_heading;
	}

	public String getSection_list_present_ic ()
	{
		return section_list_present_ic;
	}

	public void setSection_list_present_ic (String section_list_present_ic)
	{
		this.section_list_present_ic = section_list_present_ic;
	}

	public SECTION_LINKS_ZN[] getSECTION_LINKS_ZN ()
	{
		return SECTION_LINKS_ZN;
	}

	public void setSECTION_LINKS_ZN (SECTION_LINKS_ZN[] SECTION_LINKS_ZN)
	{
		this.SECTION_LINKS_ZN = SECTION_LINKS_ZN;
	}

	public String getPage_sub_title ()
	{
		return page_sub_title;
	}

	public void setPage_sub_title (String page_sub_title)
	{
		this.page_sub_title = page_sub_title;
	}

	public String getSection_list_qtt ()
	{
		return section_list_qtt;
	}

	public void setSection_list_qtt (String section_list_qtt)
	{
		this.section_list_qtt = section_list_qtt;
	}

	public String getPage_content_present_ic ()
	{
		return page_content_present_ic;
	}

	public void setPage_content_present_ic (String page_content_present_ic)
	{
		this.page_content_present_ic = page_content_present_ic;
	}

	public String getPage_content_text ()
	{
		return page_content_text;
	}

	public void setPage_content_text (String page_content_text)
	{
		this.page_content_text = page_content_text;
	}

	public String getPage_content_type_cd ()
	{
		return page_content_type_cd;
	}

	public void setPage_content_type_cd (String page_content_type_cd)
	{
		this.page_content_type_cd = page_content_type_cd;
	}

	public String getPage_description_text ()
	{
		return page_description_text;
	}

	public void setPage_description_text (String page_description_text)
	{
		this.page_description_text = page_description_text;
	}

	public String getList_pagination_ic ()
	{
		return list_pagination_ic;
	}

	public void setList_pagination_ic (String list_pagination_ic)
	{
		this.list_pagination_ic = list_pagination_ic;
	}

	public String getPage_title ()
	{
		return page_title;
	}

	public void setPage_title (String page_title)
	{
		this.page_title = page_title;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [section_list_heading = "+section_list_heading+", section_list_present_ic = "+section_list_present_ic+", SECTION_LINKS_ZN = "+SECTION_LINKS_ZN+", page_sub_title = "+page_sub_title+", section_list_qtt = "+section_list_qtt+", page_content_present_ic = "+page_content_present_ic+", page_content_text = "+page_content_text+", page_content_type_cd = "+page_content_type_cd+", page_description_text = "+page_description_text+", list_pagination_ic = "+list_pagination_ic+", page_title = "+page_title+"]";
	}
}


 class SECTION_LINKS_ZN
{
	private String link_sub_title;

	private String link_date;

	private String link_by;

	private String link_title;

	private String link_id;

	public String getLink_sub_title ()
	{
		return link_sub_title;
	}

	public void setLink_sub_title (String link_sub_title)
	{
		this.link_sub_title = link_sub_title;
	}

	public String getLink_date ()
	{
		return link_date;
	}

	public void setLink_date (String link_date)
	{
		this.link_date = link_date;
	}

	public String getLink_by ()
	{
		return link_by;
	}

	public void setLink_by (String link_by)
	{
		this.link_by = link_by;
	}

	public String getLink_title ()
	{
		return link_title;
	}

	public void setLink_title (String link_title)
	{
		this.link_title = link_title;
	}

	public String getLink_id ()
	{
		return link_id;
	}

	public void setLink_id (String link_id)
	{
		this.link_id = link_id;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [link_sub_title = "+link_sub_title+", link_date = "+link_date+", link_by = "+link_by+", link_title = "+link_title+", link_id = "+link_id+"]";
	}
}



