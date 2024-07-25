package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Banner_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_005ftest;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_005ftest.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("  <meta HTTP-EQUIV=\"Pragma\" CONTENT=\"public\">\r\n");
      out.write("  <meta HTTP-EQUIV=\"Cache-Control\" CONTENT=\"max-age=0\">\r\n");
      out.write("  <title>");
      if (_jspx_meth_s_005ftext_005f0(_jspx_page_context))
        return;
      out.write("</title>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/yui/reset-fonts-grids.css\" />\r\n");
      out.write("  <link href=\"css/openreports.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("  <!-- YUI Dependencies -->\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/yahoo-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/dom-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/event-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/animation-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/dragdrop-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/connection-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/container-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/element-beta-min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/tabview-min.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"js/yui/button-beta-min.js\"></script> \r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/yui/container.css\" />\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/yui/tabview.css\">\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/yui/button.css\">    \r\n");
      out.write("  <!-- End YUI Dependencies -->\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/highlight.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"yui-skin-sam\">\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_005ftext_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f0 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f0.setParent(null);
    _jspx_th_s_005ftext_005f0.setName("application.title");
    int _jspx_eval_s_005ftext_005f0 = _jspx_th_s_005ftext_005f0.doStartTag();
    if (_jspx_th_s_005ftext_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent(null);
    _jspx_th_s_005fif_005f0.setTest("report == null || !report.isDisplayInline()");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\r\n");
        out.write("<div class=\"menu\"> \r\n");
        out.write("\r\n");
        out.write("\t<div>\r\n");
        out.write("\t\t<ul class=\"vert\">\t\t\t\r\n");
        out.write("\t\t\t<li class=\"logo\">\t\t\t  \r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_s_005ftext_005f1(_jspx_th_s_005fif_005f0, _jspx_page_context))
          return true;
        out.write("\t\t\t\r\n");
        out.write("\t\t\t</li>                     \r\n");
        out.write("\t\t</ul>\r\n");
        out.write("\t</div>   \r\n");
        out.write("\t");
        if (_jspx_meth_s_005fif_005f1(_jspx_th_s_005fif_005f0, _jspx_page_context))
          return true;
        out.write("    \r\n");
        out.write("    \r\n");
        out.write("</div> \n");
        out.write("\n");
        out.write("<script type=\"text/javascript\">\n");
        out.write("\tvar menuButton1 = new YAHOO.widget.Button(\"logoffButton\");\n");
        out.write("\tvar menuButton2 = new YAHOO.widget.Button(\"reportsButton\");\n");
        out.write("\tvar menuButton3 = new YAHOO.widget.Button(\"adminButton\");\n");
        out.write("\tvar menuButton4 = new YAHOO.widget.Button(\"dashboardButton\");\n");
        out.write("\tvar menuButton4 = new YAHOO.widget.Button(\"schedulerButton\");\n");
        out.write("\tvar menuButton4 = new YAHOO.widget.Button(\"userAdminButton\");\n");
        out.write("</script>    \r\n");
        out.write("\r\n");
        int evalDoAfterBody = _jspx_th_s_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005ftext_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f1 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
    _jspx_th_s_005ftext_005f1.setName("application.title");
    int _jspx_eval_s_005ftext_005f1 = _jspx_th_s_005ftext_005f1.doStartTag();
    if (_jspx_th_s_005ftext_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
    _jspx_th_s_005fif_005f1.setTest("#session.breadcrumbs != null");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("  \r\n");
        out.write("\t<div id=\"usermenu\">\t    \r\n");
        out.write("  \t\t<ul class=\"vert\">   \t\n");
        out.write("  \t\t  ");
        if (_jspx_meth_s_005fif_005f2(_jspx_th_s_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("          ");
        if (_jspx_meth_s_005fif_005f4(_jspx_th_s_005fif_005f1, _jspx_page_context))
          return true;
        out.write("  \r\n");
        out.write("          ");
        if (_jspx_meth_s_005fif_005f6(_jspx_th_s_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("          ");
        if (_jspx_meth_s_005fif_005f8(_jspx_th_s_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("          ");
        if (_jspx_meth_s_005fif_005f10(_jspx_th_s_005fif_005f1, _jspx_page_context))
          return true;
        out.write("      \r\n");
        out.write("        </ul>\r\n");
        out.write("    </div>\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f1);
    _jspx_th_s_005fif_005f2.setTest("#session.user");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\t \r\n");
        out.write("          <li>\n");
        out.write("          \t<span id=\"logoffButton\" class=\"yui-button yui-link-button\"> \n");
        out.write("\t    \t\t<span class=\"first-child\"> \r\n");
        out.write("          \t\t\t<a href=\"logout.action\">");
        if (_jspx_meth_s_005ftext_005f2(_jspx_th_s_005fif_005f2, _jspx_page_context))
          return true;
        out.write("</a>\n");
        out.write("          \t\t</span>\n");
        out.write("          \t</span>                   \r\n");
        out.write("          </li>         \r\n");
        out.write("          <li>\n");
        out.write("            <span id=\"userAdminButton\" class=\"yui-button yui-link-button ");
        if (_jspx_meth_s_005fif_005f3(_jspx_th_s_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\"> \n");
        out.write("\t    \t\t<span class=\"first-child\"> \r\n");
        out.write("          \t\t\t<a href=\"userAdmin.action\">\r\n");
        out.write("          \t\t\t\t");
        if (_jspx_meth_s_005ftext_005f3(_jspx_th_s_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("          \t\t\t</a>\n");
        out.write("          \t\t</span>\n");
        out.write("          \t</span>             \r\n");
        out.write("          </li> \n");
        out.write("          ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005ftext_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f2 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f2);
    _jspx_th_s_005ftext_005f2.setName("banner.logoff");
    int _jspx_eval_s_005ftext_005f2 = _jspx_th_s_005ftext_005f2.doStartTag();
    if (_jspx_th_s_005ftext_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f3 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f2);
    _jspx_th_s_005fif_005f3.setTest("#session.breadcrumbs.startsWith('userAdmin')");
    int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
    if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f3.doInitBody();
      }
      do {
        out.write("yui-button-hover yui-link-button-hover");
        int evalDoAfterBody = _jspx_th_s_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005ftext_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f3 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f2);
    _jspx_th_s_005ftext_005f3.setName("banner.preferences");
    int _jspx_eval_s_005ftext_005f3 = _jspx_th_s_005ftext_005f3.doStartTag();
    if (_jspx_th_s_005ftext_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f4 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f1);
    _jspx_th_s_005fif_005f4.setTest("#session.user.adminUser");
    int _jspx_eval_s_005fif_005f4 = _jspx_th_s_005fif_005f4.doStartTag();
    if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("          \t<li>\n");
        out.write("          \t\t<span id=\"adminButton\" class=\"yui-button yui-link-button ");
        if (_jspx_meth_s_005fif_005f5(_jspx_th_s_005fif_005f4, _jspx_page_context))
          return true;
        out.write("\"> \n");
        out.write("\t    \t\t\t<span class=\"first-child\"> \r\n");
        out.write("            \t\t\t<a  href=\"reportAdmin.action\">\r\n");
        out.write("            \t\t\t\t");
        if (_jspx_meth_s_005ftext_005f4(_jspx_th_s_005fif_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("            \t\t\t</a>\n");
        out.write("            \t\t</span>\n");
        out.write("            \t</span>\r\n");
        out.write("            </li>\r\n");
        out.write("          ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f5 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
    _jspx_th_s_005fif_005f5.setTest("#session.breadcrumbs.startsWith('reportAdmin')");
    int _jspx_eval_s_005fif_005f5 = _jspx_th_s_005fif_005f5.doStartTag();
    if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f5.doInitBody();
      }
      do {
        out.write("yui-button-hover yui-link-button-hover");
        int evalDoAfterBody = _jspx_th_s_005fif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005ftext_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f4 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
    _jspx_th_s_005ftext_005f4.setName("banner.administration");
    int _jspx_eval_s_005ftext_005f4 = _jspx_th_s_005ftext_005f4.doStartTag();
    if (_jspx_th_s_005ftext_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f6 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f1);
    _jspx_th_s_005fif_005f6.setTest("#session.user.scheduler");
    int _jspx_eval_s_005fif_005f6 = _jspx_th_s_005fif_005f6.doStartTag();
    if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f6.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("          <li>\n");
        out.write("          \t<span id=\"schedulerButton\" class=\"yui-button yui-link-button ");
        if (_jspx_meth_s_005fif_005f7(_jspx_th_s_005fif_005f6, _jspx_page_context))
          return true;
        out.write("\"> \n");
        out.write("\t    \t\t<span class=\"first-child\"> \r\n");
        out.write("          \t\t\t<a href=\"listScheduledReports.action\">\r\n");
        out.write("          \t\t\t\t");
        if (_jspx_meth_s_005ftext_005f5(_jspx_th_s_005fif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("          \t\t\t</a>\n");
        out.write("          \t\t</span>\n");
        out.write("          \t</span> \r\n");
        out.write("          </li> \r\n");
        out.write("          ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f6);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f7 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f7.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f6);
    _jspx_th_s_005fif_005f7.setTest("#session.breadcrumbs.startsWith('listScheduledReports')");
    int _jspx_eval_s_005fif_005f7 = _jspx_th_s_005fif_005f7.doStartTag();
    if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f7.doInitBody();
      }
      do {
        out.write("yui-button-hover yui-link-button-hover");
        int evalDoAfterBody = _jspx_th_s_005fif_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f7);
    return false;
  }

  private boolean _jspx_meth_s_005ftext_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f5 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f6);
    _jspx_th_s_005ftext_005f5.setName("banner.scheduledReports");
    int _jspx_eval_s_005ftext_005f5 = _jspx_th_s_005ftext_005f5.doStartTag();
    if (_jspx_th_s_005ftext_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f8 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f8.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f1);
    _jspx_th_s_005fif_005f8.setTest("#session.user");
    int _jspx_eval_s_005fif_005f8 = _jspx_th_s_005fif_005f8.doStartTag();
    if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f8.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("          <li>\n");
        out.write("          \t<span id=\"reportsButton\" class=\"yui-button yui-link-button ");
        if (_jspx_meth_s_005fif_005f9(_jspx_th_s_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\"> \n");
        out.write("\t    \t\t<span class=\"first-child\"> \r\n");
        out.write("          \t\t\t<a href=\"reportGroup.action\">\r\n");
        out.write("          \t\t\t\t");
        if (_jspx_meth_s_005ftext_005f6(_jspx_th_s_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("          \t\t\t</a>\n");
        out.write("          \t\t</span>\n");
        out.write("          \t</span> \r\n");
        out.write("          </li>\n");
        out.write("          ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f8);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f9 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f8);
    _jspx_th_s_005fif_005f9.setTest("#session.breadcrumbs.startsWith('reportGroup')");
    int _jspx_eval_s_005fif_005f9 = _jspx_th_s_005fif_005f9.doStartTag();
    if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f9.doInitBody();
      }
      do {
        out.write("yui-button-hover yui-link-button-hover");
        int evalDoAfterBody = _jspx_th_s_005fif_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f9);
    return false;
  }

  private boolean _jspx_meth_s_005ftext_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f6 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f8);
    _jspx_th_s_005ftext_005f6.setName("banner.reports");
    int _jspx_eval_s_005ftext_005f6 = _jspx_th_s_005ftext_005f6.doStartTag();
    if (_jspx_th_s_005ftext_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f6);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f10 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f10.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f1);
    _jspx_th_s_005fif_005f10.setTest("#session.user.dashboardUser");
    int _jspx_eval_s_005fif_005f10 = _jspx_th_s_005fif_005f10.doStartTag();
    if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f10.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("          <li>\n");
        out.write("          \t<span id=\"dashboardButton\" class=\"yui-button yui-link-button ");
        if (_jspx_meth_s_005fif_005f11(_jspx_th_s_005fif_005f10, _jspx_page_context))
          return true;
        out.write("\"> \n");
        out.write("\t    \t\t<span class=\"first-child\"> \r\n");
        out.write("          \t\t\t<a href=\"dashboard.action\">\r\n");
        out.write("          \t\t\t\t");
        if (_jspx_meth_s_005ftext_005f7(_jspx_th_s_005fif_005f10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("          \t\t\t</a>\n");
        out.write("          \t\t</span>\n");
        out.write("          \t</span>          \r\n");
        out.write("          </li>\r\n");
        out.write("          ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f10);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f11 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f11.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f10);
    _jspx_th_s_005fif_005f11.setTest("#session.breadcrumbs.startsWith('dashboard')");
    int _jspx_eval_s_005fif_005f11 = _jspx_th_s_005fif_005f11.doStartTag();
    if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f11.doInitBody();
      }
      do {
        out.write("yui-button-hover yui-link-button-hover");
        int evalDoAfterBody = _jspx_th_s_005fif_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_005ftest.reuse(_jspx_th_s_005fif_005f11);
    return false;
  }

  private boolean _jspx_meth_s_005ftext_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:text
    org.apache.struts2.views.jsp.TextTag _jspx_th_s_005ftext_005f7 = (org.apache.struts2.views.jsp.TextTag) _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.get(org.apache.struts2.views.jsp.TextTag.class);
    _jspx_th_s_005ftext_005f7.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftext_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f10);
    _jspx_th_s_005ftext_005f7.setName("banner.dashboard");
    int _jspx_eval_s_005ftext_005f7 = _jspx_th_s_005ftext_005f7.doStartTag();
    if (_jspx_th_s_005ftext_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftext_005fname_005fnobody.reuse(_jspx_th_s_005ftext_005f7);
    return false;
  }
}
