/**
 * Autogenerated by Thrift Compiler (0.18.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package io.github.donnie4w.tim.stub;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.18.1)", date = "2023-11-21")
public class TimStream implements org.apache.thrift.TBase<TimStream, TimStream._Fields>, java.io.Serializable, Cloneable, Comparable<TimStream> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TimStream");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField VNODE_FIELD_DESC = new org.apache.thrift.protocol.TField("VNode", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DTYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("dtype", org.apache.thrift.protocol.TType.BYTE, (short)3);
  private static final org.apache.thrift.protocol.TField BODY_FIELD_DESC = new org.apache.thrift.protocol.TField("body", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField FROM_NODE_FIELD_DESC = new org.apache.thrift.protocol.TField("fromNode", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TimStreamStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TimStreamTupleSchemeFactory();

  public long id; // required
  public @org.apache.thrift.annotation.Nullable String VNode; // required
  public byte dtype; // optional
  public @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer body; // optional
  public @org.apache.thrift.annotation.Nullable String fromNode; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    VNODE((short)2, "VNode"),
    DTYPE((short)3, "dtype"),
    BODY((short)4, "body"),
    FROM_NODE((short)5, "fromNode");

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
        case 1: // ID
          return ID;
        case 2: // VNODE
          return VNODE;
        case 3: // DTYPE
          return DTYPE;
        case 4: // BODY
          return BODY;
        case 5: // FROM_NODE
          return FROM_NODE;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __DTYPE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.DTYPE,_Fields.BODY};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.VNODE, new org.apache.thrift.meta_data.FieldMetaData("VNode", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DTYPE, new org.apache.thrift.meta_data.FieldMetaData("dtype", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BYTE)));
    tmpMap.put(_Fields.BODY, new org.apache.thrift.meta_data.FieldMetaData("body", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    tmpMap.put(_Fields.FROM_NODE, new org.apache.thrift.meta_data.FieldMetaData("fromNode", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TimStream.class, metaDataMap);
  }

  public TimStream() {
  }

  public TimStream(
    long id,
    String VNode,
    String fromNode)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.VNode = VNode;
    this.fromNode = fromNode;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TimStream(TimStream other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetVNode()) {
      this.VNode = other.VNode;
    }
    this.dtype = other.dtype;
    if (other.isSetBody()) {
      this.body = org.apache.thrift.TBaseHelper.copyBinary(other.body);
    }
    if (other.isSetFromNode()) {
      this.fromNode = other.fromNode;
    }
  }

  @Override
  public TimStream deepCopy() {
    return new TimStream(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.VNode = null;
    setDtypeIsSet(false);
    this.dtype = 0;
    this.body = null;
    this.fromNode = null;
  }

  public long getId() {
    return this.id;
  }

  public TimStream setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public String getVNode() {
    return this.VNode;
  }

  public TimStream setVNode(@org.apache.thrift.annotation.Nullable String VNode) {
    this.VNode = VNode;
    return this;
  }

  public void unsetVNode() {
    this.VNode = null;
  }

  /** Returns true if field VNode is set (has been assigned a value) and false otherwise */
  public boolean isSetVNode() {
    return this.VNode != null;
  }

  public void setVNodeIsSet(boolean value) {
    if (!value) {
      this.VNode = null;
    }
  }

  public byte getDtype() {
    return this.dtype;
  }

  public TimStream setDtype(byte dtype) {
    this.dtype = dtype;
    setDtypeIsSet(true);
    return this;
  }

  public void unsetDtype() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __DTYPE_ISSET_ID);
  }

  /** Returns true if field dtype is set (has been assigned a value) and false otherwise */
  public boolean isSetDtype() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __DTYPE_ISSET_ID);
  }

  public void setDtypeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __DTYPE_ISSET_ID, value);
  }

  public byte[] getBody() {
    setBody(org.apache.thrift.TBaseHelper.rightSize(body));
    return body == null ? null : body.array();
  }

  public java.nio.ByteBuffer bufferForBody() {
    return org.apache.thrift.TBaseHelper.copyBinary(body);
  }

  public TimStream setBody(byte[] body) {
    this.body = body == null ? (java.nio.ByteBuffer)null   : java.nio.ByteBuffer.wrap(body.clone());
    return this;
  }

  public TimStream setBody(@org.apache.thrift.annotation.Nullable java.nio.ByteBuffer body) {
    this.body = org.apache.thrift.TBaseHelper.copyBinary(body);
    return this;
  }

  public void unsetBody() {
    this.body = null;
  }

  /** Returns true if field body is set (has been assigned a value) and false otherwise */
  public boolean isSetBody() {
    return this.body != null;
  }

  public void setBodyIsSet(boolean value) {
    if (!value) {
      this.body = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public String getFromNode() {
    return this.fromNode;
  }

  public TimStream setFromNode(@org.apache.thrift.annotation.Nullable String fromNode) {
    this.fromNode = fromNode;
    return this;
  }

  public void unsetFromNode() {
    this.fromNode = null;
  }

  /** Returns true if field fromNode is set (has been assigned a value) and false otherwise */
  public boolean isSetFromNode() {
    return this.fromNode != null;
  }

  public void setFromNodeIsSet(boolean value) {
    if (!value) {
      this.fromNode = null;
    }
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case VNODE:
      if (value == null) {
        unsetVNode();
      } else {
        setVNode((String)value);
      }
      break;

    case DTYPE:
      if (value == null) {
        unsetDtype();
      } else {
        setDtype((Byte)value);
      }
      break;

    case BODY:
      if (value == null) {
        unsetBody();
      } else {
        if (value instanceof byte[]) {
          setBody((byte[])value);
        } else {
          setBody((java.nio.ByteBuffer)value);
        }
      }
      break;

    case FROM_NODE:
      if (value == null) {
        unsetFromNode();
      } else {
        setFromNode((String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case VNODE:
      return getVNode();

    case DTYPE:
      return getDtype();

    case BODY:
      return getBody();

    case FROM_NODE:
      return getFromNode();

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
    case ID:
      return isSetId();
    case VNODE:
      return isSetVNode();
    case DTYPE:
      return isSetDtype();
    case BODY:
      return isSetBody();
    case FROM_NODE:
      return isSetFromNode();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that instanceof TimStream)
      return this.equals((TimStream)that);
    return false;
  }

  public boolean equals(TimStream that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_VNode = true && this.isSetVNode();
    boolean that_present_VNode = true && that.isSetVNode();
    if (this_present_VNode || that_present_VNode) {
      if (!(this_present_VNode && that_present_VNode))
        return false;
      if (!this.VNode.equals(that.VNode))
        return false;
    }

    boolean this_present_dtype = true && this.isSetDtype();
    boolean that_present_dtype = true && that.isSetDtype();
    if (this_present_dtype || that_present_dtype) {
      if (!(this_present_dtype && that_present_dtype))
        return false;
      if (this.dtype != that.dtype)
        return false;
    }

    boolean this_present_body = true && this.isSetBody();
    boolean that_present_body = true && that.isSetBody();
    if (this_present_body || that_present_body) {
      if (!(this_present_body && that_present_body))
        return false;
      if (!this.body.equals(that.body))
        return false;
    }

    boolean this_present_fromNode = true && this.isSetFromNode();
    boolean that_present_fromNode = true && that.isSetFromNode();
    if (this_present_fromNode || that_present_fromNode) {
      if (!(this_present_fromNode && that_present_fromNode))
        return false;
      if (!this.fromNode.equals(that.fromNode))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

    hashCode = hashCode * 8191 + ((isSetVNode()) ? 131071 : 524287);
    if (isSetVNode())
      hashCode = hashCode * 8191 + VNode.hashCode();

    hashCode = hashCode * 8191 + ((isSetDtype()) ? 131071 : 524287);
    if (isSetDtype())
      hashCode = hashCode * 8191 + (int) (dtype);

    hashCode = hashCode * 8191 + ((isSetBody()) ? 131071 : 524287);
    if (isSetBody())
      hashCode = hashCode * 8191 + body.hashCode();

    hashCode = hashCode * 8191 + ((isSetFromNode()) ? 131071 : 524287);
    if (isSetFromNode())
      hashCode = hashCode * 8191 + fromNode.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TimStream other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.compare(isSetId(), other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetVNode(), other.isSetVNode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVNode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.VNode, other.VNode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetDtype(), other.isSetDtype());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDtype()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dtype, other.dtype);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetBody(), other.isSetBody());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBody()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.body, other.body);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetFromNode(), other.isSetFromNode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFromNode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fromNode, other.fromNode);
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
    StringBuilder sb = new StringBuilder("TimStream(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("VNode:");
    if (this.VNode == null) {
      sb.append("null");
    } else {
      sb.append(this.VNode);
    }
    first = false;
    if (isSetDtype()) {
      if (!first) sb.append(", ");
      sb.append("dtype:");
      sb.append(this.dtype);
      first = false;
    }
    if (isSetBody()) {
      if (!first) sb.append(", ");
      sb.append("body:");
      if (this.body == null) {
        sb.append("null");
      } else {
        org.apache.thrift.TBaseHelper.toString(this.body, sb);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("fromNode:");
    if (this.fromNode == null) {
      sb.append("null");
    } else {
      sb.append(this.fromNode);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'id' because it's a primitive and you chose the non-beans generator.
    if (VNode == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'VNode' was not present! Struct: " + toString());
    }
    if (fromNode == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'fromNode' was not present! Struct: " + toString());
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

  private static class TimStreamStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TimStreamStandardScheme getScheme() {
      return new TimStreamStandardScheme();
    }
  }

  private static class TimStreamStandardScheme extends org.apache.thrift.scheme.StandardScheme<TimStream> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, TimStream struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VNODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.VNode = iprot.readString();
              struct.setVNodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DTYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.BYTE) {
              struct.dtype = iprot.readByte();
              struct.setDtypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // BODY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.body = iprot.readBinary();
              struct.setBodyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // FROM_NODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fromNode = iprot.readString();
              struct.setFromNodeIsSet(true);
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
      if (!struct.isSetId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, TimStream struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.VNode != null) {
        oprot.writeFieldBegin(VNODE_FIELD_DESC);
        oprot.writeString(struct.VNode);
        oprot.writeFieldEnd();
      }
      if (struct.isSetDtype()) {
        oprot.writeFieldBegin(DTYPE_FIELD_DESC);
        oprot.writeByte(struct.dtype);
        oprot.writeFieldEnd();
      }
      if (struct.body != null) {
        if (struct.isSetBody()) {
          oprot.writeFieldBegin(BODY_FIELD_DESC);
          oprot.writeBinary(struct.body);
          oprot.writeFieldEnd();
        }
      }
      if (struct.fromNode != null) {
        oprot.writeFieldBegin(FROM_NODE_FIELD_DESC);
        oprot.writeString(struct.fromNode);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TimStreamTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TimStreamTupleScheme getScheme() {
      return new TimStreamTupleScheme();
    }
  }

  private static class TimStreamTupleScheme extends org.apache.thrift.scheme.TupleScheme<TimStream> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TimStream struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.id);
      oprot.writeString(struct.VNode);
      oprot.writeString(struct.fromNode);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDtype()) {
        optionals.set(0);
      }
      if (struct.isSetBody()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetDtype()) {
        oprot.writeByte(struct.dtype);
      }
      if (struct.isSetBody()) {
        oprot.writeBinary(struct.body);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TimStream struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.id = iprot.readI64();
      struct.setIdIsSet(true);
      struct.VNode = iprot.readString();
      struct.setVNodeIsSet(true);
      struct.fromNode = iprot.readString();
      struct.setFromNodeIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.dtype = iprot.readByte();
        struct.setDtypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.body = iprot.readBinary();
        struct.setBodyIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

