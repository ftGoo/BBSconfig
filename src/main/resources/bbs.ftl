<BBSConfig>

  <order>xpath</order>
  <fetchInterval>1000</fetchInterval>
  <monitorInterval>${interval}</monitorInterval>
  <host>${host}</host>
  <name>${name}</name>
  <charset>${charset}</charset>
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
    
    <listItemURL>${listitemurl}</listItemURL>
    <replyCount>${replycount}</replyCount>
	<reviewCount>${reviewcount}</reviewCount>
    <!-- 最新回复人 -->
    <lastReplyAuthor>${lastRepelyAuthor}</lastReplyAuthor>
    <!-- 最新回复时间 -->
    <lastReplyTime>${lastrepelytime}</lastReplyTime>
    <listNextPage>${listnextpage}</listNextPage>
    <listMaxPage>1</listMaxPage>
  </listConfig>
  
  <threadConfig>
    <title>${title}</title>
    <author>${author}</author>
    <content>${content}</content>
    <postTime>${posttime}</postTime>
    <threadNextPage>${threadnextpage}</threadNextPage>
    <threadMaxPage>0</threadMaxPage>
  </threadConfig>
  
</BBSConfig>