/**
 * 根据id获取所有父级id
 * @param {String} id 
 * @param {Array} children 
 */
function byIdGetSuperiors(id, children) {
    let array = []
    let method = (children) => {
        for (let item of children) {
            if (item.value === id) {
                array.push(item.value)
                return id;
            }
            if (item.children) {
                if (array.lastIndexOf(id) === -1) {
                    let temp = method(item.children)
                    if (temp) {
                        array.push(item.value)
                        return temp
                    }
                }
            }
        }
    }
    method(children)
    return array.reverse()
}

/**
 * 根据label获取所有父级label集合
 * @param {String} label
 * @param {Array} children
 */
function byIdGetLabels(id, children) {
    let array = []
    let method = (children) => {
        for (let item of children) {
            if (item.label === id) {
                array.push(item.label)
                return id;
            }
            if (item.children) {
                if (array.lastIndexOf(id) === -1) {
                    let temp = method(item.children)
                    if (temp) {
                        array.push(item.label)
                        return temp
                    }
                }
            }
        }
    }
    method(children)
    return array.reverse()
}

/**
 * 根据id获取对应的中文label
 * @param {String} id 
 * @param {Array} children 
 */
function byIdGetLabel(id, children) {
    for (let item of children) {
        if (item.value === id) {
            return item.label;
        }
        if (item.children) {
            let label = byIdGetLabel(id, item.children)
            if (label) {
                return label
            }
        }
    }
}

function createCascaderOptions(children) {
    let list = []
    let temp;
    for (let item of children) {
        temp = {
            value: item.codeid,
            label: item.comment
        }
        if (item.basecodeforms.length > 0) {
            temp.children = createCascaderOptions(item.basecodeforms)
        }
        list.push(temp)
    }
    return list;
}

/**
 * 根据id获取所有父级id,如果id为数组,返回
 * @param {String} id
 * @param {Array} children
 */
function getArrayByStringId(id, children) {
    if(id instanceof Array){
        //判断id是否已经为数组
        return id

    }else {
        return byIdGetSuperiors(id, children)
    }
}

/**
 * 判断中文字段获取id
 */
function getIdByName(name,children){
    for (let item of children) {
        if (item.label === name) {
            return item.value;
        }
        if (item.children) {
            let value = getIdByName(name, item.children)
            if (value) {
                return value
            }
        }
    }
}
/**
 * 生成随机key
 */
function createKey(){

}

function getDateFormat(date){
    if(!date) return '';
    // ios兼容处理
    if(typeof date === 'string') {
        date = date.replace(/-/g, '/');
        date = date.replace(/[a-z]|[A-Z]/g, ' ');
        date = date.substring(0, date.indexOf('.'));
        date = new Date(date);
    }
    const year = date.getFullYear();
    const mon=date.getMonth()+1;
    const day=date.getDate();
    return year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day);
}

function getDateTimeFormat(date){
    // ios兼容处理
	if(typeof date === 'string') {
		date = date.replace(/-/g, '/');
		date = date.replace(/[a-z]|[A-Z]/g, ' ');
		if(date.indexOf('.') > -1){
			date = date.substring(0, date.indexOf('.'));
		}
		date = new Date(date);
	}
	let year = date.getFullYear();
	let mon=date.getMonth()+1;
	let day=date.getDate();
	let hour = date.getHours();
	
	let min = date.getMinutes();
	let sec = date.getSeconds();
	let s = year+"-"
            +(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day) 
            + ' '
            + (hour < 10? '0' + hour : hour) 
            + ':' 
            + (min < 10? '0' + min : min) 
            + ':' 
            + (sec < 10? '0' + sec : sec);
	return s;
}

export { byIdGetSuperiors, byIdGetLabel, createCascaderOptions,getArrayByStringId ,byIdGetLabels,getIdByName, getDateFormat, getDateTimeFormat}