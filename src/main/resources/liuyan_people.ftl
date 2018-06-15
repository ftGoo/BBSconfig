<BBSConfig>

  <order>xpath</order>
  <fetchInterval>1000</fetchInterval>
  <monitorInterval>${interval}</monitorInterval>
  <host>liuyan.people.com</host>
  <name>人民网</name>
  <charset>gbk</charset>
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
    
    <listItemURL>//DIV[@class='clearfix list_box']/UL/LI/H2/B/A/@href</listItemURL>
    <listNextPage>//A[text()='>']/@href</listNextPage>
    <listMaxPage>1</listMaxPage>
  </listConfig>
  
  <threadConfig>
    <title>//H2/B</title>
    <author></author>
    <content>//P[@id='zoom']</content>
    <postTime>//H3</postTime>
  </threadConfig>
</BBSConfig>