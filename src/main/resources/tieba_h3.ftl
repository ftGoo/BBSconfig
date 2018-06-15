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
    
    <regex><![CDATA[<code class="pagelet_html" id="pagelet_html_frs-list\/pagelet\/thread_list" style="display\:none;">([\s\S]*)]]></regex>
    <listItemURL>//A[@class='j_th_tit ']/@href</listItemURL>
    <listNextPage>//A[@class='next pagination-item ']/@href</listNextPage>
    <replyCount>//SPAN[contains(@class,'threadlist_rep_num center_text')]/text()</replyCount>
    <listMaxPage>2</listMaxPage>
  </listConfig>
  <threadConfig>
    <author>(//DIV[contains(@class,'d_post_content_main')]/DIV[contains(@class,'p_content')]/CC/../../../DIV[contains(@class,'d_author')]/UL/LI[@class='d_name'])|(//DIV[contains(@class,'d_post_content_main')]/DIV[contains(@class,'p_content')]/CC/../../../DIV[contains(@class,'d_d_author_anonym')])</author>
    <title>//H3[contains(@class, 'core_title_txt')]</title>
    <content>//DIV[contains(@class,'d_post_content_main')]/DIV[contains(@class,'p_content')]/CC</content>
    <postTime>//DIV[contains(@class,'l_post')]/DIV[2]/DIV[contains(@class, 'core_reply')]/DIV[contains(@class, 'core_reply_tail')]/DIV[@class='post-tail-wrap']/SPAN[last()]</postTime>
    <threadNextPage>//LI[contains(@class,'l_pager')]/A[text()='下一页']/@href</threadNextPage>
    <threadMaxPage>0</threadMaxPage>
  </threadConfig>
</BBSConfig>