// written by Dean Edwards, 2005
// with input from Tino Zijdel, Matthias Miller, Diego Perini

// http://dean.edwards.name/weblog/2005/10/add-event/

//IE<9会给元素添加
//$$events : 对象{'click':{'guid':function(){},...},'dbclick':{...}...} guid++
//不同事件的监听函数的$$guid可能是相同的

function addEvent(element, type, handler) {
	if (element.addEventListener) {
		element.addEventListener(type, handler, false);
	} else {
		// assign each event handler a unique ID
		if (!handler.$$guid) handler.$$guid = addEvent.guid++;
		// create a hash table of event types for the element
		if (!element.$$events) element.$$events = {};
		// create a hash table of event handlers for each element/event pair
		var handlers = element.$$events[type];
		if (!handlers) {
			handlers = element.$$events[type] = {};
			// store the existing event handler (if there is one)
			if (element["on" + type]) {
				handlers[0] = element["on" + type];
			}
		}
		// store the event handler in the hash table
        //修改:如果是第一个就不再添加
        if(handlers[0]!=handler){
          handlers[handler.$$guid] = handler;
        }
       
		// assign a global event handler to do all the work
		element["on" + type] = handleEvent;
	}
};
// a counter used to create unique IDs
addEvent.guid = 1;

function removeEvent(element, type, handler) {
	if (element.removeEventListener) {
		element.removeEventListener(type, handler, false);
	} else {
		// delete the event handler from the hash table
		if (element.$$events && element.$$events[type]) {
            //修改:如果是第一个就删除第一个而忽略$$guid
            var guid=element.$$events[type][0]==handler ? 0 : handler.$$guid;
            delete element.$$events[type][guid];
		}
	}
};

function handleEvent(event) {
	var returnValue = true;
	// grab the event object (IE uses a global event object)
	event = event || fixEvent(((this.ownerDocument || this.document || this).parentWindow || window).event);
	// get a reference to the hash table of event handlers
	var handlers = this.$$events[event.type];
	// execute each event handler
	for (var i in handlers) {
        //修改:不使用添加属性的方式改变this,用call代替
		//this.$$handleEvent = handlers[i];
		//if (this.$$handleEvent(event) === false) {
        if (handlers[i].call(this,event) === false) {
			returnValue = false;
		}
	}
	return returnValue;
};

function fixEvent(event) {
	// add W3C standard event methods
	event.target = event.srcElement;
	event.preventDefault = fixEvent.preventDefault;
	event.stopPropagation = fixEvent.stopPropagation;
    //添加:pageX,pageY兼容
    event.pageX=event.clientX+document.documentElement.scrollLeft;
    event.pageY=event.clientY+document.documentElement.scrollTop;	
	return event;
};

fixEvent.preventDefault = function() {
	this.returnValue = false;
};

fixEvent.stopPropagation = function() {
	this.cancelBubble = true;
};
