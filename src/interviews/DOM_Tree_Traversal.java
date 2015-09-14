package interviews;

public class DOM_Tree_Traversal {
	/**
	 * 这道题就是说给你一个DOM树，这个树里面有两种节点，一种节点叫Element，这种节点里面有三种元素：
	=> tag,比如<html>...</html>中的<html>就是tag；
	=> id, 比如<div id="foo">中的foo就是id: 
	=> a list of children，可以是任何子节点； 	
	
	还有一种节点叫Content，比如<body>some content</body>里面的some content就是Content节点
	然后让你写个程序来represent这个DOM树，输出的每一行表示DOM树的每一层。每一行都要有tag,id（如果存在）
	和content（如果存在），另外还有个条件就是给你一个white list,如果这个DOM树里面的元素是在white 
	list里面，那个就ignore DOM树里面的这个元素。

	举例：
<html>
     <body id="content"> 
           This
           <div id="wrapper1">
           is a
                 <div id="container1">
                       <div id="container2">
                               funny
                       </div> 
                 <div id="container2"> 
                        enjoyable
                 </div>
                 little
           </div>
           good example
           <div id="wrapper4">
           </div>
           <a id="link">
                   Add a link
           </a> 
     </body>
</html>

	whitelist = {"wrapper4","a little"}
	所以output就是：
	html
	body content This good example
	div wrapper1 a link Add a link
	 */
	
	
}
