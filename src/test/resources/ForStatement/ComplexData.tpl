Orders:
{ for order in client.orders }
${order#index}
Id: ${order.id}
Date: ${order.date}
Client: ${order.client.name}
{ endfor }