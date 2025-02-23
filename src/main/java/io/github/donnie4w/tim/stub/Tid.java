/**
 * Autogenerated by Thrift Compiler (0.21.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package io.github.donnie4w.tim.stub;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.21.0)", date = "2025-01-09")
public class Tid implements org.apache.thrift.TBase<Tid, Tid._Fields>, java.io.Serializable, Cloneable, Comparable<Tid> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Tid");

  private static final org.apache.thrift.protocol.TField NODE_FIELD_DESC = new org.apache.thrift.protocol.TField("node", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DOMAIN_FIELD_DESC = new org.apache.thrift.protocol.TField("domain", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField RESOURCE_FIELD_DESC = new org.apache.thrift.protocol.TField("resource", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField TERMTYP_FIELD_DESC = new org.apache.thrift.protocol.TField("termtyp", org.apache.thrift.protocol.TType.BYTE, (short)4);
  private static final org.apache.thrift.protocol.TField EXTEND_FIELD_DESC = new org.apache.thrift.protocol.TField("extend", org.apache.thrift.protocol.TType.MAP, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TidStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TidTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable String node; // required
  public @org.apache.thrift.annotation.Nullable String domain; // optional
  public @org.apache.thrift.annotation.Nullable String resource; // optional
  public byte termtyp; // optional
  public @org.apache.thrift.annotation.Nullable java.util.Map<String, String> extend; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NODE((short)1, "node"),
    DOMAIN((short)2, "domain"),
    RESOURCE((short)3, "resource"),
    TERMTYP((short)4, "termtyp"),
    EXTEND((short)5, "extend");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NODE
          return NODE;
        case 2: // DOMAIN
          return DOMAIN;
        case 3: // RESOURCE
          return RESOURCE;
        case 4: // TERMTYP
          return TERMTYP;
        case 5: // EXTEND
          return EXTEND;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TERMTYP_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.DOMAIN,_Fields.RESOURCE,_Fields.TERMTYP,_Fields.EXTEND};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NODE, new org.apache.thrift.meta_data.FieldMetaData("node", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DOMAIN, new org.apache.thrift.meta_data.FieldMetaData("domain", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RESOURCE, new org.apache.thrift.meta_data.FieldMetaData("resource", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TERMTYP, new org.apache.thrift.meta_data.FieldMetaData("termtyp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BYTE)));
    tmpMap.put(_Fields.EXTEND, new org.apache.thrift.meta_data.FieldMetaData("extend", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Tid.class, metaDataMap);
  }

  public Tid() {
  }

  public Tid(
    String node)
  {
    this();
    this.node = node;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Tid(Tid other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetNode()) {
      this.node = other.node;
    }
    if (other.isSetDomain()) {
      this.domain = other.domain;
    }
    if (other.isSetResource()) {
      this.resource = other.resource;
    }
    this.termtyp = other.termtyp;
    if (other.isSetExtend()) {
      java.util.Map<String, String> __this__extend = new java.util.HashMap<String, String>(other.extend);
      this.extend = __this__extend;
    }
  }

  @Override
  public Tid deepCopy() {
    return new Tid(this);
  }

  @Override
  public void clear() {
    this.node = null;
    this.domain = null;
    this.resource = null;
    setTermtypIsSet(false);
    this.termtyp = 0;
    this.extend = null;
  }

  @org.apache.thrift.annotation.Nullable
  public String getNode() {
    return this.node;
  }

  public Tid setNode(@org.apache.thrift.annotation.Nullable String node) {
    this.node = node;
    return this;
  }

  public void unsetNode() {
    this.node = null;
  }

  /** Returns true if field node is set (has been assigned a value) and false otherwise */
  public boolean isSetNode() {
    return this.node != null;
  }

  public void setNodeIsSet(boolean value) {
    if (!value) {
      this.node = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public String getDomain() {
    return this.domain;
  }

  public Tid setDomain(@org.apache.thrift.annotation.Nullable String domain) {
    this.domain = domain;
    return this;
  }

  public void unsetDomain() {
    this.domain = null;
  }

  /** Returns true if field domain is set (has been assigned a value) and false otherwise */
  public boolean isSetDomain() {
    return this.domain != null;
  }

  public void setDomainIsSet(boolean value) {
    if (!value) {
      this.domain = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public String getResource() {
    return this.resource;
  }

  public Tid setResource(@org.apache.thrift.annotation.Nullable String resource) {
    this.resource = resource;
    return this;
  }

  public void unsetResource() {
    this.resource = null;
  }

  /** Returns true if field resource is set (has been assigned a value) and false otherwise */
  public boolean isSetResource() {
    return this.resource != null;
  }

  public void setResourceIsSet(boolean value) {
    if (!value) {
      this.resource = null;
    }
  }

  public byte getTermtyp() {
    return this.termtyp;
  }

  public Tid setTermtyp(byte termtyp) {
    this.termtyp = termtyp;
    setTermtypIsSet(true);
    return this;
  }

  public void unsetTermtyp() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TERMTYP_ISSET_ID);
  }

  /** Returns true if field termtyp is set (has been assigned a value) and false otherwise */
  public boolean isSetTermtyp() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TERMTYP_ISSET_ID);
  }

  public void setTermtypIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TERMTYP_ISSET_ID, value);
  }

  public int getExtendSize() {
    return (this.extend == null) ? 0 : this.extend.size();
  }

  public void putToExtend(String key, String val) {
    if (this.extend == null) {
      this.extend = new java.util.HashMap<String, String>();
    }
    this.extend.put(key, val);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Map<String, String> getExtend() {
    return this.extend;
  }

  public Tid setExtend(@org.apache.thrift.annotation.Nullable java.util.Map<String, String> extend) {
    this.extend = extend;
    return this;
  }

  public void unsetExtend() {
    this.extend = null;
  }

  /** Returns true if field extend is set (has been assigned a value) and false otherwise */
  public boolean isSetExtend() {
    return this.extend != null;
  }

  public void setExtendIsSet(boolean value) {
    if (!value) {
      this.extend = null;
    }
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case NODE:
      if (value == null) {
        unsetNode();
      } else {
        setNode((String)value);
      }
      break;

    case DOMAIN:
      if (value == null) {
        unsetDomain();
      } else {
        setDomain((String)value);
      }
      break;

    case RESOURCE:
      if (value == null) {
        unsetResource();
      } else {
        setResource((String)value);
      }
      break;

    case TERMTYP:
      if (value == null) {
        unsetTermtyp();
      } else {
        setTermtyp((Byte)value);
      }
      break;

    case EXTEND:
      if (value == null) {
        unsetExtend();
      } else {
        setExtend((java.util.Map<String, String>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NODE:
      return getNode();

    case DOMAIN:
      return getDomain();

    case RESOURCE:
      return getResource();

    case TERMTYP:
      return getTermtyp();

    case EXTEND:
      return getExtend();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NODE:
      return isSetNode();
    case DOMAIN:
      return isSetDomain();
    case RESOURCE:
      return isSetResource();
    case TERMTYP:
      return isSetTermtyp();
    case EXTEND:
      return isSetExtend();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that instanceof Tid)
      return this.equals((Tid)that);
    return false;
  }

  public boolean equals(Tid that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_node = true && this.isSetNode();
    boolean that_present_node = true && that.isSetNode();
    if (this_present_node || that_present_node) {
      if (!(this_present_node && that_present_node))
        return false;
      if (!this.node.equals(that.node))
        return false;
    }

    boolean this_present_domain = true && this.isSetDomain();
    boolean that_present_domain = true && that.isSetDomain();
    if (this_present_domain || that_present_domain) {
      if (!(this_present_domain && that_present_domain))
        return false;
      if (!this.domain.equals(that.domain))
        return false;
    }

    boolean this_present_resource = true && this.isSetResource();
    boolean that_present_resource = true && that.isSetResource();
    if (this_present_resource || that_present_resource) {
      if (!(this_present_resource && that_present_resource))
        return false;
      if (!this.resource.equals(that.resource))
        return false;
    }

    boolean this_present_termtyp = true && this.isSetTermtyp();
    boolean that_present_termtyp = true && that.isSetTermtyp();
    if (this_present_termtyp || that_present_termtyp) {
      if (!(this_present_termtyp && that_present_termtyp))
        return false;
      if (this.termtyp != that.termtyp)
        return false;
    }

    boolean this_present_extend = true && this.isSetExtend();
    boolean that_present_extend = true && that.isSetExtend();
    if (this_present_extend || that_present_extend) {
      if (!(this_present_extend && that_present_extend))
        return false;
      if (!this.extend.equals(that.extend))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetNode()) ? 131071 : 524287);
    if (isSetNode())
      hashCode = hashCode * 8191 + node.hashCode();

    hashCode = hashCode * 8191 + ((isSetDomain()) ? 131071 : 524287);
    if (isSetDomain())
      hashCode = hashCode * 8191 + domain.hashCode();

    hashCode = hashCode * 8191 + ((isSetResource()) ? 131071 : 524287);
    if (isSetResource())
      hashCode = hashCode * 8191 + resource.hashCode();

    hashCode = hashCode * 8191 + ((isSetTermtyp()) ? 131071 : 524287);
    if (isSetTermtyp())
      hashCode = hashCode * 8191 + (int) (termtyp);

    hashCode = hashCode * 8191 + ((isSetExtend()) ? 131071 : 524287);
    if (isSetExtend())
      hashCode = hashCode * 8191 + extend.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Tid other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.compare(isSetNode(), other.isSetNode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.node, other.node);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetDomain(), other.isSetDomain());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDomain()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.domain, other.domain);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetResource(), other.isSetResource());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResource()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resource, other.resource);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetTermtyp(), other.isSetTermtyp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTermtyp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.termtyp, other.termtyp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetExtend(), other.isSetExtend());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExtend()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.extend, other.extend);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Tid(");
    boolean first = true;

    sb.append("node:");
    if (this.node == null) {
      sb.append("null");
    } else {
      sb.append(this.node);
    }
    first = false;
    if (isSetDomain()) {
      if (!first) sb.append(", ");
      sb.append("domain:");
      if (this.domain == null) {
        sb.append("null");
      } else {
        sb.append(this.domain);
      }
      first = false;
    }
    if (isSetResource()) {
      if (!first) sb.append(", ");
      sb.append("resource:");
      if (this.resource == null) {
        sb.append("null");
      } else {
        sb.append(this.resource);
      }
      first = false;
    }
    if (isSetTermtyp()) {
      if (!first) sb.append(", ");
      sb.append("termtyp:");
      sb.append(this.termtyp);
      first = false;
    }
    if (isSetExtend()) {
      if (!first) sb.append(", ");
      sb.append("extend:");
      if (this.extend == null) {
        sb.append("null");
      } else {
        sb.append(this.extend);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (node == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'node' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TidStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TidStandardScheme getScheme() {
      return new TidStandardScheme();
    }
  }

  private static class TidStandardScheme extends org.apache.thrift.scheme.StandardScheme<Tid> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, Tid struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.node = iprot.readString();
              struct.setNodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DOMAIN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.domain = iprot.readString();
              struct.setDomainIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // RESOURCE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.resource = iprot.readString();
              struct.setResourceIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TERMTYP
            if (schemeField.type == org.apache.thrift.protocol.TType.BYTE) {
              struct.termtyp = iprot.readByte();
              struct.setTermtypIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // EXTEND
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
                struct.extend = new java.util.HashMap<String, String>(2*_map0.size);
                @org.apache.thrift.annotation.Nullable String _key1;
                @org.apache.thrift.annotation.Nullable String _val2;
                for (int _i3 = 0; _i3 < _map0.size; ++_i3)
                {
                  _key1 = iprot.readString();
                  _val2 = iprot.readString();
                  struct.extend.put(_key1, _val2);
                }
                iprot.readMapEnd();
              }
              struct.setExtendIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, Tid struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.node != null) {
        oprot.writeFieldBegin(NODE_FIELD_DESC);
        oprot.writeString(struct.node);
        oprot.writeFieldEnd();
      }
      if (struct.domain != null) {
        if (struct.isSetDomain()) {
          oprot.writeFieldBegin(DOMAIN_FIELD_DESC);
          oprot.writeString(struct.domain);
          oprot.writeFieldEnd();
        }
      }
      if (struct.resource != null) {
        if (struct.isSetResource()) {
          oprot.writeFieldBegin(RESOURCE_FIELD_DESC);
          oprot.writeString(struct.resource);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetTermtyp()) {
        oprot.writeFieldBegin(TERMTYP_FIELD_DESC);
        oprot.writeByte(struct.termtyp);
        oprot.writeFieldEnd();
      }
      if (struct.extend != null) {
        if (struct.isSetExtend()) {
          oprot.writeFieldBegin(EXTEND_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.extend.size()));
            for (java.util.Map.Entry<String, String> _iter4 : struct.extend.entrySet())
            {
              oprot.writeString(_iter4.getKey());
              oprot.writeString(_iter4.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TidTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TidTupleScheme getScheme() {
      return new TidTupleScheme();
    }
  }

  private static class TidTupleScheme extends org.apache.thrift.scheme.TupleScheme<Tid> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Tid struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.node);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDomain()) {
        optionals.set(0);
      }
      if (struct.isSetResource()) {
        optionals.set(1);
      }
      if (struct.isSetTermtyp()) {
        optionals.set(2);
      }
      if (struct.isSetExtend()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetDomain()) {
        oprot.writeString(struct.domain);
      }
      if (struct.isSetResource()) {
        oprot.writeString(struct.resource);
      }
      if (struct.isSetTermtyp()) {
        oprot.writeByte(struct.termtyp);
      }
      if (struct.isSetExtend()) {
        {
          oprot.writeI32(struct.extend.size());
          for (java.util.Map.Entry<String, String> _iter5 : struct.extend.entrySet())
          {
            oprot.writeString(_iter5.getKey());
            oprot.writeString(_iter5.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Tid struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.node = iprot.readString();
      struct.setNodeIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.domain = iprot.readString();
        struct.setDomainIsSet(true);
      }
      if (incoming.get(1)) {
        struct.resource = iprot.readString();
        struct.setResourceIsSet(true);
      }
      if (incoming.get(2)) {
        struct.termtyp = iprot.readByte();
        struct.setTermtypIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TMap _map6 = iprot.readMapBegin(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING); 
          struct.extend = new java.util.HashMap<String, String>(2*_map6.size);
          @org.apache.thrift.annotation.Nullable String _key7;
          @org.apache.thrift.annotation.Nullable String _val8;
          for (int _i9 = 0; _i9 < _map6.size; ++_i9)
          {
            _key7 = iprot.readString();
            _val8 = iprot.readString();
            struct.extend.put(_key7, _val8);
          }
        }
        struct.setExtendIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

