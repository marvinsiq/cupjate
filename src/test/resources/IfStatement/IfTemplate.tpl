{ if trueValue }
This block is visible because the value is true.
{ endif }

{ if not trueValue }
This block is not visible because the value is inverted.
{ endif }

{ if notSetValue }
This block is visible because the variable 'notSetValue' is not set,
{ else }
This text will not be displayed.
{ endif }

{ if nullValue }
A null value.
{ else }
This text will not be displayed.
{ endif }

{ if emptyValue }
An empty string value.
{ else }
This text will not be displayed.
{ endif }

{ if emptyValue }
An empty list
{ else }
This text will not be displayed.
{ endif }

{ if zeroValue }
A zero value.
{ else }
This text will not be displayed.
{ endif }

{ if falseValue }
This text will not be displayed.
{ else }
A false value
{ endif }

{ if trueValue }
this block is visible if the value is true, other than null and not empty
{ else }
This text will not be displayed.
{ endif }

{ if anyValueNotConsideredFalse }
this block is visible if the value is true, other than null and not empty
{ else }
This text will not be displayed.
{ endif }