Client: ${client.name}

Orders:

{ for order in client.orders }
	Order #${order#count}
	Id: ${order.id}
	Date: ${order.date}
	Itens:{ for item in order.itens }
		Item ${item#count} - ${item.name}{ endfor }
{ endfor }
Phones:
{ for phone in client.phones }
	${phone}{ endfor }