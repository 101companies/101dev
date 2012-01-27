package mega.trace.transformation;

/*
 * How to obtain a reference to the tracer when performing a callback?
 * TLINK_STATIC: static reference. Fastest, but only possible when there is only one tracer
 * TLINK_IDMAP: use a HashMap and unique id (much slower)(default)
 */


public enum TracerLink {
	//kicked out: DIRTY,FIELD
	TLINK_STATIC,TLINK_IDMAP

}
