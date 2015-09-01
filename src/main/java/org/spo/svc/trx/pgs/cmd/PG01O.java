package org.spo.svc.trx.pgs.cmd;

import java.util.List;

public class PG01O
{
	private String page_sub_title;

	private MAIN_SECTION MAIN_SECTION;

	private String page_title;

	public String getPage_sub_title ()
	{
		return page_sub_title;
	}

	public void setPage_sub_title (String page_sub_title)
	{
		this.page_sub_title = page_sub_title;
	}

	public MAIN_SECTION getMAIN_SECTION ()
	{
		return MAIN_SECTION;
	}

	public void setMAIN_SECTION (MAIN_SECTION MAIN_SECTION)
	{
		this.MAIN_SECTION = MAIN_SECTION;
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
		return "ClassPojo [page_sub_title = "+page_sub_title+", MAIN_SECTION = "+MAIN_SECTION+", page_title = "+page_title+"]";
	}
}





class MAIN_SECTION
{
	private String more_pages_link_ic;

	private String section_title;

	private String section_links_att;

	private SECTION_LINKS_ZN[] SECTION_LINKS_ZN;

	private String link_qtt;

	private String section_id;

	public String getMore_pages_link_ic ()
	{
		return more_pages_link_ic;
	}

	public void setMore_pages_link_ic (String more_pages_link_ic)
	{
		this.more_pages_link_ic = more_pages_link_ic;
	}

	public String getSection_title ()
	{
		return section_title;
	}

	public void setSection_title (String section_title)
	{
		this.section_title = section_title;
	}

	public String getSection_links_att ()
	{
		return section_links_att;
	}

	public void setSection_links_att (String section_links_att)
	{
		this.section_links_att = section_links_att;
	}

	public SECTION_LINKS_ZN[] getSECTION_LINKS_ZN ()
	{
		return SECTION_LINKS_ZN;
	}

	public void setSECTION_LINKS_ZN (SECTION_LINKS_ZN[] SECTION_LINKS_ZN)
	{
		this.SECTION_LINKS_ZN = SECTION_LINKS_ZN;
	}

	public String getLink_qtt ()
	{
		return link_qtt;
	}

	public void setLink_qtt (String link_qtt)
	{
		this.link_qtt = link_qtt;
	}

	public String getSection_id ()
	{
		return section_id;
	}

	public void setSection_id (String section_id)
	{
		this.section_id = section_id;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [more_pages_link_ic = "+more_pages_link_ic+", section_title = "+section_title+", section_links_att = "+section_links_att+", SECTION_LINKS_ZN = "+SECTION_LINKS_ZN+", link_qtt = "+link_qtt+", section_id = "+section_id+"]";
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