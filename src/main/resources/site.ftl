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
  <type>bbs</type>
  
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
    
    <listItemURL></listItemURL>
    <listNextPage></listNextPage>
    <listMaxPage>1</listMaxPage>
  </listConfig>
  
  <threadConfig>
    <title></title>
    <author></author>
    <content></content>
    <postTime></postTime>
  </threadConfig>
</BBSConfig>