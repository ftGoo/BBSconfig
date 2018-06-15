<BBSConfig>

  <order>xpath</order>
  <fetchInterval>500</fetchInterval>
  <monitorInterval>${interval}</monitorInterval>
  <host>bbs.tianya.cn</host>
  <name>天涯</name>
  <timeLocale>CN</timeLocale>
  <charset>utf8</charset>
  <maxFetchThreads>${maxFetchThreads}</maxFetchThreads>
  <language>CN</language>
  
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
    
    <listItemURL>//TR/TD[@class]/A[1]/@href</listItemURL>
    <listNextPage>//A[not(@target) and contains(text(),'下一页')]/@href</listNextPage>
    <replyCount>//TR/TD[@class]/following-sibling::TD[3]</replyCount>
    <!-- 浏览数 -->
    <reviewCount>//TR/TD[@class]/following-sibling::TD[2]</reviewCount>
    <lastReplyAuthor></lastReplyAuthor>
    <lastReplyTime>//TR/TD[@class]/following-sibling::TD[4]</lastReplyTime>
    <listMaxPage>2</listMaxPage>
  </listConfig>
  
  <threadConfig>
    <author>//DIV[@class='atl-info']/SPAN[1]/A</author>
    <title>//H1/SPAN[1]</title>
    <postTime>//DIV[@class='atl-info']/SPAN[contains(text(),'时间')]</postTime>
    <content>//DIV[contains(@class,'bbs-content')]</content>
    <replyStartIndex>1</replyStartIndex>
    <threadNextPage>//A[@class='js-keyboard-next']/@href</threadNextPage>
    <threadMaxPage>0</threadMaxPage>
  </threadConfig>
</BBSConfig>