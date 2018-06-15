<BBSConfig>

  <order>regex-xpath</order>
  <fetchInterval>500</fetchInterval>
  <monitorInterval>5</monitorInterval>
  <host>tieba.baidu.com</host>
  <name>百度贴吧</name>
  <charset>utf8</charset>
  <maxFetchThreads>${maxFetchThreads}</maxFetchThreads>
  <timeLocale>CN</timeLocale>
  <language>中文</language>
  
  <listConfig>
    
    <boardConfigs>
        <#list boardConfigs as boardConfig>
        	<boardConfig>
        	    <url>${boardConfig.url}</url>
        	    <name>${boardConfig.name}</name>
        	    <genus>${boardConfig.genus}</genus>
        	</boardConfig>
        </#list>
    </boardConfigs>
    
    <regex><![CDATA[<code class=\"pagelet_html\" id=\"pagelet_html_frs-list\\/pagelet\\/thread_list\" style=\"display\\:none;\">([\\s\\S]*)]]></regex>
    <listItemURL>//DIV[contains(@class,'threadlist_title') and not (I[@alt='置顶'])]/A[@class='j_th_tit ']/@href</listItemURL>
    <listNextPage>//A[contains(@class,'next pagination-item')]/@href</listNextPage>
    <replyCount>//DIV[contains(@class,'threadlist_title') and not (I[@alt='置顶'])]/A[1]/@href/../../../../../DIV[1]/SPAN/text()</replyCount>
    <listMaxPage>2</listMaxPage>
  </listConfig>
  
  <threadConfig>
    <author>(//DIV[contains(@class,'d_author')]/UL/LI[@class='d_name' and not (A[contains(@href, '/link?')])])|(//DIV[@class='d_author_anonym'])</author>
    <title>//H1</title>
    <content>//DIV[contains(@class,'d_post_content_main')]/DIV[contains(@class,'p_content')]/CC</content>
    <postTime>//DIV[contains(@class,'l_post')]/@data-field</postTime>
	<pubtimePattern>date"\:"(.*)","vote</pubtimePattern>
    <threadNextPage>//LI[contains(@class,'l_pager')]/A[text()='下一页']/@href</threadNextPage>
    <threadMaxPage>5</threadMaxPage>
  </threadConfig>
</BBSConfig>