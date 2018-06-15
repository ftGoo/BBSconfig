<BBSConfig>

  <order>xpath</order>
  <fetchInterval>1000</fetchInterval>
  <monitorInterval>${interval}</monitorInterval>
  <host>xici.net</host>
  <name>西祠胡同</name>
  <charset>gbk</charset>
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
	  
	<listExtractor>com.fantasi.yuqing.acq.bbs.orderProcessor.XiciListExtractor</listExtractor>
	  
  </listConfig>
  
  <threadConfig>
	<threadExtractor>com.fantasi.yuqing.acq.bbs.orderProcessor.XiciThreadExtractor</threadExtractor>
    <replyStartIndex>1</replyStartIndex>
    <threadNextPage>//DIV[@id='page']/A[@name='nextPage']/@href</threadNextPage>
    <threadMaxPage>0</threadMaxPage>
  </threadConfig>
</BBSConfig>